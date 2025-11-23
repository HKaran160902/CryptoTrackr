package com.hari.pdd.cryptotrackr.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hari.pdd.cryptotrackr.data.local.entity.CoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Query("SELECT * FROM coins ORDER BY marketCapRank ASC")
    fun getAllCoins(): Flow<List<CoinEntity>>

    @Query("SELECT * FROM coins WHERE id = :coinId")
    suspend fun getCoinById(coinId: String): CoinEntity?

    @Query("SELECT * FROM coins WHERE id = :coinId")
    fun getCoinByIdFlow(coinId: String): Flow<CoinEntity?>

    @Query("SELECT * FROM coins WHERE id IN (:coinIds)")
    fun getCoinsByIds(coinIds: List<String>): Flow<List<CoinEntity>>

    @Query("SELECT * FROM coins WHERE name LIKE '%' || :query || '%' OR symbol LIKE '%' || :query || '%'")
    fun searchCoins(query: String): Flow<List<CoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<CoinEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coin: CoinEntity)

    @Query("DELETE FROM coins")
    suspend fun deleteAllCoins()

    @Query("DELETE FROM coins WHERE cachedAt < :timestamp")
    suspend fun deleteOldCoins(timestamp: Long)

    @Query("SELECT COUNT(*) FROM coins")
    suspend fun getCoinsCount(): Int

    @Query("SELECT MAX(cachedAt) FROM coins")
    suspend fun getLastCacheTime(): Long?
}
