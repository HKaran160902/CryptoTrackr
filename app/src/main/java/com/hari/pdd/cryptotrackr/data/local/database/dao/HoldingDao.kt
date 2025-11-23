package com.hari.pdd.cryptotrackr.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hari.pdd.cryptotrackr.data.local.entity.HoldingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HoldingDao {

    @Query("SELECT * FROM holdings ORDER BY purchaseDate DESC")
    fun getAllHoldings(): Flow<List<HoldingEntity>>

    @Query("SELECT * FROM holdings WHERE id = :holdingId")
    suspend fun getHoldingById(holdingId: Long): HoldingEntity?

    @Query("SELECT * FROM holdings WHERE coinId = :coinId")
    fun getHoldingsByCoinId(coinId: String): Flow<List<HoldingEntity>>

    @Query("SELECT DISTINCT coinId FROM holdings")
    fun getAllHeldCoinIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHolding(holding: HoldingEntity): Long

    @Update
    suspend fun updateHolding(holding: HoldingEntity)

    @Delete
    suspend fun deleteHolding(holding: HoldingEntity)

    @Query("DELETE FROM holdings WHERE id = :holdingId")
    suspend fun deleteHoldingById(holdingId: Long)

    @Query("DELETE FROM holdings")
    suspend fun clearAllHoldings()

    @Query("SELECT COUNT(*) FROM holdings")
    suspend fun getHoldingsCount(): Int

    @Query("SELECT SUM(quantity * purchasePrice) FROM holdings")
    fun getTotalInvested(): Flow<Double?>

    @Query("SELECT SUM(quantity) FROM holdings WHERE coinId = :coinId")
    suspend fun getTotalQuantityForCoin(coinId: String): Double?
}
