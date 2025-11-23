package com.hari.pdd.cryptotrackr.domain.repository

import com.hari.pdd.cryptotrackr.domain.model.PriceAlert
import kotlinx.coroutines.flow.Flow

interface AlertRepository {

    fun getAllAlerts(): Flow<List<PriceAlert>>

    fun getActiveAlerts(): Flow<List<PriceAlert>>

    suspend fun getActiveAlertsSync(): List<PriceAlert>

    suspend fun getAlertById(alertId: Long): PriceAlert?

    fun getAlertsForCoin(coinId: String): Flow<List<PriceAlert>>

    suspend fun addAlert(alert: PriceAlert): Long

    suspend fun updateAlert(alert: PriceAlert)

    suspend fun deleteAlert(alert: PriceAlert)

    suspend fun deleteAlertById(alertId: Long)

    suspend fun setAlertEnabled(alertId: Long, isEnabled: Boolean)

    suspend fun markAlertTriggered(alertId: Long)

    suspend fun clearAllAlerts()

    suspend fun getActiveAlertsCount(): Int

    suspend fun checkAndTriggerAlerts(currentPrices: Map<String, Double>): List<PriceAlert>
}
