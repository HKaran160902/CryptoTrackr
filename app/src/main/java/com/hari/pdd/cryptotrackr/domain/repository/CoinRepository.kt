package com.hari.pdd.cryptotrackr.domain.repository

import com.hari.pdd.cryptotrackr.domain.model.ChartData
import com.hari.pdd.cryptotrackr.domain.model.Coin
import com.hari.pdd.cryptotrackr.domain.model.CoinDetail
import com.hari.pdd.cryptotrackr.util.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(
        forceRefresh: Boolean = false,
        page: Int = 1,
        perPage: Int = 50
    ): Flow<Resource<List<Coin>>>

    fun getCoinById(coinId: String): Flow<Resource<Coin>>

    fun getCoinDetail(coinId: String): Flow<Resource<CoinDetail>>

    fun getMarketChart(
        coinId: String,
        days: String = "1"
    ): Flow<Resource<ChartData>>

    fun searchCoins(query: String): Flow<Resource<List<Coin>>>

    fun getCoinsByIds(ids: List<String>): Flow<Resource<List<Coin>>>

    suspend fun refreshCoins()

    suspend fun clearCache()
}
