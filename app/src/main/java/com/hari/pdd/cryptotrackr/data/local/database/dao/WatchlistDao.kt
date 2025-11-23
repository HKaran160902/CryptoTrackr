package com.hari.pdd.cryptotrackr.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hari.pdd.cryptotrackr.data.local.entity.WatchlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchlistDao {

    @Query("SELECT * FROM watchlist ORDER BY addedAt DESC")
    fun getAllWatchlistItems(): Flow<List<WatchlistEntity>>

    @Query("SELECT coinId FROM watchlist")
    fun getAllWatchlistCoinIds(): Flow<List<String>>

    @Query("SELECT EXISTS(SELECT 1 FROM watchlist WHERE coinId = :coinId)")
    fun isInWatchlist(coinId: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM watchlist WHERE coinId = :coinId)")
    suspend fun isInWatchlistSync(coinId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchlist(item: WatchlistEntity)

    @Query("DELETE FROM watchlist WHERE coinId = :coinId")
    suspend fun removeFromWatchlist(coinId: String)

    @Delete
    suspend fun deleteWatchlistItem(item: WatchlistEntity)

    @Query("DELETE FROM watchlist")
    suspend fun clearWatchlist()

    @Query("SELECT COUNT(*) FROM watchlist")
    suspend fun getWatchlistCount(): Int
}
