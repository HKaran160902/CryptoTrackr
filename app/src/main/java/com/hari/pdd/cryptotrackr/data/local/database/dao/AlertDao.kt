package com.hari.pdd.cryptotrackr.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hari.pdd.cryptotrackr.data.local.entity.AlertEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlertDao {

    @Query("SELECT * FROM alerts ORDER BY createdAt DESC")
    fun getAllAlerts(): Flow<List<AlertEntity>>

    @Query("SELECT * FROM alerts WHERE isEnabled = 1 AND isTriggered = 0")
    fun getActiveAlerts(): Flow<List<AlertEntity>>

    @Query("SELECT * FROM alerts WHERE isEnabled = 1 AND isTriggered = 0")
    suspend fun getActiveAlertsSync(): List<AlertEntity>

    @Query("SELECT * FROM alerts WHERE id = :alertId")
    suspend fun getAlertById(alertId: Long): AlertEntity?

    @Query("SELECT * FROM alerts WHERE coinId = :coinId")
    fun getAlertsForCoin(coinId: String): Flow<List<AlertEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlert(alert: AlertEntity): Long

    @Update
    suspend fun updateAlert(alert: AlertEntity)

    @Delete
    suspend fun deleteAlert(alert: AlertEntity)

    @Query("DELETE FROM alerts WHERE id = :alertId")
    suspend fun deleteAlertById(alertId: Long)

    @Query("UPDATE alerts SET isEnabled = :isEnabled WHERE id = :alertId")
    suspend fun setAlertEnabled(alertId: Long, isEnabled: Boolean)

    @Query("UPDATE alerts SET isTriggered = 1, triggeredAt = :triggeredAt WHERE id = :alertId")
    suspend fun markAlertTriggered(alertId: Long, triggeredAt: Long = System.currentTimeMillis())

    @Query("DELETE FROM alerts")
    suspend fun clearAllAlerts()

    @Query("SELECT COUNT(*) FROM alerts WHERE isEnabled = 1 AND isTriggered = 0")
    suspend fun getActiveAlertsCount(): Int
}
