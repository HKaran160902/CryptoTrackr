package com.hari.pdd.cryptotrackr.data.repository

import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao
import com.hari.pdd.cryptotrackr.data.local.database.dao.WatchlistDao
import com.hari.pdd.cryptotrackr.data.local.entity.WatchlistEntity
import com.hari.pdd.cryptotrackr.data.mapper.CoinMapper.toDomain
import com.hari.pdd.cryptotrackr.data.mapper.CoinMapper.toEntity
import com.hari.pdd.cryptotrackr.data.remote.api.CoinGeckoApi
import com.hari.pdd.cryptotrackr.domain.model.Coin
import com.hari.pdd.cryptotrackr.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchlistRepositoryImpl @Inject constructor(
    private val watchlistDao: WatchlistDao,
    private val coinDao: CoinDao,
    private val api: CoinGeckoApi
) : WatchlistRepository {

    override fun getWatchlistCoins(): Flow<List<Coin>> {
        return watchlistDao.getAllWatchlistCoinIds().flatMapLatest { coinIds ->
            if (coinIds.isEmpty()) {
                flow { emit(emptyList()) }
            } else {
                // Try to get from cache first
                coinDao.getCoinsByIds(coinIds).map { cachedCoins ->
                    val cachedMap = cachedCoins.associateBy { it.id }

                    // Check if we have all coins in cache
                    if (cachedCoins.size == coinIds.size) {
                        coinIds.mapNotNull { id -> cachedMap[id]?.toDomain()?.copy(isInWatchlist = true) }
                    } else {
                        // Fetch missing coins from API
                        try {
                            val response = api.getCoinsByIds(ids = coinIds.joinToString(","))
                            response.forEach { coin ->
                                coinDao.insertCoin(coin.toEntity())
                            }
                            response.map { it.toDomain().copy(isInWatchlist = true) }
                        } catch (e: Exception) {
                            // Return whatever we have in cache
                            coinIds.mapNotNull { id -> cachedMap[id]?.toDomain()?.copy(isInWatchlist = true) }
                        }
                    }
                }
            }
        }
    }

    override fun getWatchlistCoinIds(): Flow<List<String>> {
        return watchlistDao.getAllWatchlistCoinIds()
    }

    override fun isInWatchlist(coinId: String): Flow<Boolean> {
        return watchlistDao.isInWatchlist(coinId)
    }

    override suspend fun addToWatchlist(coinId: String) {
        watchlistDao.addToWatchlist(WatchlistEntity(coinId = coinId))
    }

    override suspend fun removeFromWatchlist(coinId: String) {
        watchlistDao.removeFromWatchlist(coinId)
    }

    override suspend fun toggleWatchlist(coinId: String) {
        if (watchlistDao.isInWatchlistSync(coinId)) {
            removeFromWatchlist(coinId)
        } else {
            addToWatchlist(coinId)
        }
    }

    override suspend fun clearWatchlist() {
        watchlistDao.clearWatchlist()
    }

    override suspend fun getWatchlistCount(): Int {
        return watchlistDao.getWatchlistCount()
    }
}
