package com.hari.pdd.cryptotrackr.data.repository

import com.hari.pdd.cryptotrackr.data.local.database.dao.AlertDao
import com.hari.pdd.cryptotrackr.data.mapper.CoinMapper.toDomain
import com.hari.pdd.cryptotrackr.data.mapper.CoinMapper.toEntity
import com.hari.pdd.cryptotrackr.domain.model.PriceAlert
import com.hari.pdd.cryptotrackr.domain.repository.AlertRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlertRepositoryImpl @Inject constructor(
    private val alertDao: AlertDao
) : AlertRepository {

    override fun getAllAlerts(): Flow<List<PriceAlert>> {
        return alertDao.getAllAlerts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getActiveAlerts(): Flow<List<PriceAlert>> {
        return alertDao.getActiveAlerts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getActiveAlertsSync(): List<PriceAlert> {
        return alertDao.getActiveAlertsSync().map { it.toDomain() }
    }

    override suspend fun getAlertById(alertId: Long): PriceAlert? {
        return alertDao.getAlertById(alertId)?.toDomain()
    }

    override fun getAlertsForCoin(coinId: String): Flow<List<PriceAlert>> {
        return alertDao.getAlertsForCoin(coinId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addAlert(alert: PriceAlert): Long {
        return alertDao.insertAlert(alert.toEntity())
    }

    override suspend fun updateAlert(alert: PriceAlert) {
        alertDao.updateAlert(alert.toEntity())
    }

    override suspend fun deleteAlert(alert: PriceAlert) {
        alertDao.deleteAlert(alert.toEntity())
    }

    override suspend fun deleteAlertById(alertId: Long) {
        alertDao.deleteAlertById(alertId)
    }

    override suspend fun setAlertEnabled(alertId: Long, isEnabled: Boolean) {
        alertDao.setAlertEnabled(alertId, isEnabled)
    }

    override suspend fun markAlertTriggered(alertId: Long) {
        alertDao.markAlertTriggered(alertId)
    }

    override suspend fun clearAllAlerts() {
        alertDao.clearAllAlerts()
    }

    override suspend fun getActiveAlertsCount(): Int {
        return alertDao.getActiveAlertsCount()
    }

    override suspend fun checkAndTriggerAlerts(currentPrices: Map<String, Double>): List<PriceAlert> {
        val activeAlerts = getActiveAlertsSync()
        val triggeredAlerts = mutableListOf<PriceAlert>()

        activeAlerts.forEach { alert ->
            val currentPrice = currentPrices[alert.coinId]
            if (currentPrice != null && alert.shouldTrigger(currentPrice)) {
                markAlertTriggered(alert.id)
                triggeredAlerts.add(alert)
            }
        }

        return triggeredAlerts
    }
}
