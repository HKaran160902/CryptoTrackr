package com.hari.pdd.cryptotrackr.data.repository

import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao
import com.hari.pdd.cryptotrackr.data.local.preferences.UserPreferences
import com.hari.pdd.cryptotrackr.data.mapper.CoinMapper.toDomain
import com.hari.pdd.cryptotrackr.data.mapper.CoinMapper.toEntity
import com.hari.pdd.cryptotrackr.data.remote.api.CoinGeckoApi
import com.hari.pdd.cryptotrackr.domain.model.ChartData
import com.hari.pdd.cryptotrackr.domain.model.Coin
import com.hari.pdd.cryptotrackr.domain.model.CoinDetail
import com.hari.pdd.cryptotrackr.domain.repository.CoinRepository
import com.hari.pdd.cryptotrackr.util.Constants
import com.hari.pdd.cryptotrackr.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepositoryImpl @Inject constructor(
    private val api: CoinGeckoApi,
    private val coinDao: CoinDao,
    private val userPreferences: UserPreferences
) : CoinRepository {

    override fun getCoins(
        forceRefresh: Boolean, page: Int, perPage: Int
    ): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())

        // Get selected currency
        val currency = userPreferences.currency.first()

        // First, emit cached data if available
        val cachedCoins = coinDao.getAllCoins()
        cachedCoins.collect { coins ->
            if (coins.isNotEmpty() && !forceRefresh) {
                val lastCacheTime = coinDao.getLastCacheTime() ?: 0
                val isCacheValid =
                    System.currentTimeMillis() - lastCacheTime < Constants.CACHE_DURATION_MS

                if (isCacheValid) {
                    emit(Resource.Success(coins.map { it.toDomain() }))
                    return@collect
                }
            }

            // Fetch from network
            try {
                val response = api.getCoinsMarkets(
                    vsCurrency = currency, page = page, perPage = perPage
                )

                // Cache the response
                val entities = response.map { it.toEntity() }
                coinDao.insertCoins(entities)

                emit(Resource.Success(response.map { it.toDomain() }))
            } catch (e: Exception) {
                // If network fails, emit cached data if available
                if (coins.isNotEmpty()) {
                    emit(Resource.Success(coins.map { it.toDomain() }))
                } else {
                    emit(Resource.Error(e.message ?: "Failed to fetch coins"))
                }
            }
        }
    }

    override fun getCoinById(coinId: String): Flow<Resource<Coin>> = flow {
        emit(Resource.Loading())

        // Get selected currency
        val currency = userPreferences.currency.first()

        // Try cache first
        val cachedCoin = coinDao.getCoinById(coinId)
        if (cachedCoin != null) {
            emit(Resource.Success(cachedCoin.toDomain()))
        }

        // Fetch fresh data
        try {
            val response = api.getCoinsByIds(ids = coinId, vsCurrency = currency)
            if (response.isNotEmpty()) {
                val coin = response.first()
                coinDao.insertCoin(coin.toEntity())
                emit(Resource.Success(coin.toDomain()))
            } else {
                if (cachedCoin == null) {
                    emit(Resource.Error("Coin not found"))
                }
            }
        } catch (e: Exception) {
            if (cachedCoin == null) {
                emit(Resource.Error(e.message ?: "Failed to fetch coin"))
            }
        }
    }

    override fun getCoinDetail(coinId: String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading())

        // Try to get cached basic coin data first - emit immediately if available
        val cachedCoin = coinDao.getCoinById(coinId)

        // If we have cached data, show it immediately while fetching fresh data
        if (cachedCoin != null) {
            val cachedDetail = CoinDetail(
                id = cachedCoin.id,
                symbol = cachedCoin.symbol,
                name = cachedCoin.name,
                image = cachedCoin.image,
                currentPrice = cachedCoin.currentPrice ?: 0.0,
                marketCap = cachedCoin.marketCap ?: 0L,
                marketCapRank = cachedCoin.marketCapRank ?: 0,
                totalVolume = cachedCoin.totalVolume ?: 0L,
                high24h = cachedCoin.high24h ?: 0.0,
                low24h = cachedCoin.low24h ?: 0.0,
                priceChange24h = cachedCoin.priceChange24h ?: 0.0,
                priceChangePercentage24h = cachedCoin.priceChangePercentage24h ?: 0.0,
                priceChangePercentage7d = cachedCoin.priceChangePercentage7d ?: 0.0,
                priceChangePercentage30d = 0.0,
                priceChangePercentage1y = 0.0,
                circulatingSupply = cachedCoin.circulatingSupply ?: 0.0,
                totalSupply = cachedCoin.totalSupply,
                maxSupply = cachedCoin.maxSupply,
                ath = cachedCoin.ath ?: 0.0,
                athChangePercentage = cachedCoin.athChangePercentage ?: 0.0,
                athDate = cachedCoin.athDate,
                atl = cachedCoin.atl ?: 0.0,
                atlChangePercentage = cachedCoin.atlChangePercentage ?: 0.0,
                atlDate = cachedCoin.atlDate,
                lastUpdated = cachedCoin.lastUpdated,
                description = "",
                sparklineData = emptyList()
            )
            emit(Resource.Success(cachedDetail))
        }

        // Try to fetch fresh data from API
        try {
            val response = api.getCoinDetail(coinId)
            emit(Resource.Success(response.toDomain()))
        } catch (e: Exception) {
            // If network failed and we already emitted cache, just log the error
            // If no cache was available, emit error
            if (cachedCoin == null) {
                emit(Resource.Error(e.message ?: "Failed to fetch coin details"))
            }
            // Otherwise we already showed cached data, user can retry manually
        }
    }

    override fun getMarketChart(coinId: String, days: String): Flow<Resource<ChartData>> = flow {
        emit(Resource.Loading())

        // Get selected currency
        val currency = userPreferences.currency.first()

        try {
            val response = api.getMarketChart(coinId = coinId, vsCurrency = currency, days = days)
            emit(Resource.Success(response.toDomain()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Failed to fetch chart data"))
        }
    }

    override fun searchCoins(query: String): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())

        // Get selected currency
        val currency = userPreferences.currency.first()

        // Search in cache first
        coinDao.searchCoins(query).collect { cachedResults ->
            if (cachedResults.isNotEmpty()) {
                emit(Resource.Success(cachedResults.map { it.toDomain() }))
            }
        }

        // Also search via API for better results
        try {
            val searchResponse = api.searchCoins(query)
            val coinIds = searchResponse.coins?.take(20)?.joinToString(",") { it.id } ?: ""

            if (coinIds.isNotEmpty()) {
                val coins = api.getCoinsByIds(ids = coinIds, vsCurrency = currency)
                emit(Resource.Success(coins.map { it.toDomain() }))
            }
        } catch (_: Exception) {
            // If API fails, we already emitted cache results
        }
    }

    override fun getCoinsByIds(ids: List<String>): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())

        // Get selected currency
        val currency = userPreferences.currency.first()

        try {
            val idsString = ids.joinToString(",")
            val response = api.getCoinsByIds(ids = idsString, vsCurrency = currency)
            emit(Resource.Success(response.map { it.toDomain() }))
        } catch (e: Exception) {
            // Try cache
            coinDao.getCoinsByIds(ids).collect { cachedCoins ->
                if (cachedCoins.isNotEmpty()) {
                    emit(Resource.Success(cachedCoins.map { it.toDomain() }))
                } else {
                    emit(Resource.Error(e.message ?: "Failed to fetch coins"))
                }
            }
        }
    }

    override suspend fun refreshCoins() {
        try {
            // Get selected currency
            val currency = userPreferences.currency.first()

            val response = api.getCoinsMarkets(vsCurrency = currency)
            val entities = response.map { it.toEntity() }
            coinDao.deleteAllCoins()
            coinDao.insertCoins(entities)
        } catch (_: Exception) {
            // Handle error silently for background refresh
        }
    }

    override suspend fun clearCache() {
        coinDao.deleteAllCoins()
    }
}
