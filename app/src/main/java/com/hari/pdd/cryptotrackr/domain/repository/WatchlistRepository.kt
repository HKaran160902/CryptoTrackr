package com.hari.pdd.cryptotrackr.domain.repository

import com.hari.pdd.cryptotrackr.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface WatchlistRepository {

    fun getWatchlistCoins(): Flow<List<Coin>>

    fun getWatchlistCoinIds(): Flow<List<String>>

    fun isInWatchlist(coinId: String): Flow<Boolean>

    suspend fun addToWatchlist(coinId: String)

    suspend fun removeFromWatchlist(coinId: String)

    suspend fun toggleWatchlist(coinId: String)

    suspend fun clearWatchlist()

    suspend fun getWatchlistCount(): Int
}
