package com.hari.pdd.cryptotrackr.worker

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hari.pdd.cryptotrackr.CryptoTrackrApp
import com.hari.pdd.cryptotrackr.MainActivity
import com.hari.pdd.cryptotrackr.R
import com.hari.pdd.cryptotrackr.data.remote.api.CoinGeckoApi
import com.hari.pdd.cryptotrackr.domain.repository.AlertRepository
import com.hari.pdd.cryptotrackr.util.Constants
import com.hari.pdd.cryptotrackr.util.formatPrice
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PriceAlertWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val alertRepository: AlertRepository,
    private val coinGeckoApi: CoinGeckoApi
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            // Get active alerts
            val activeAlerts = alertRepository.getActiveAlertsSync()

            if (activeAlerts.isEmpty()) {
                return Result.success()
            }

            // Get unique coin IDs
            val coinIds = activeAlerts.map { it.coinId }.distinct().joinToString(",")

            // Fetch current prices
            val coins = coinGeckoApi.getCoinsByIds(ids = coinIds)
            val priceMap = coins.associate { it.id to (it.currentPrice ?: 0.0) }

            // Check and trigger alerts
            val triggeredAlerts = alertRepository.checkAndTriggerAlerts(priceMap)

            // Send notifications for triggered alerts
            triggeredAlerts.forEach { alert ->
                val currentPrice = priceMap[alert.coinId] ?: 0.0
                sendNotification(alert.coinName, currentPrice, alert.targetPrice, alert.isAbove)
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private fun sendNotification(
        coinName: String,
        currentPrice: Double,
        targetPrice: Double,
        isAbove: Boolean
    ) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val condition = if (isAbove) "above" else "below"
        val title = "$coinName Price Alert"
        val message = "$coinName is now ${condition} your target price of ${targetPrice.formatPrice()}. Current price: ${currentPrice.formatPrice()}"

        val notification = NotificationCompat.Builder(context, CryptoTrackrApp.PRICE_ALERT_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            System.currentTimeMillis().toInt(),
            notification
        )
    }
}
