package com.hari.pdd.cryptotrackr.data.remote.api

import com.hari.pdd.cryptotrackr.data.remote.dto.CoinDetailDto
import com.hari.pdd.cryptotrackr.data.remote.dto.CoinDto
import com.hari.pdd.cryptotrackr.data.remote.dto.MarketChartDto
import com.hari.pdd.cryptotrackr.data.remote.dto.SearchResponseDto
import com.hari.pdd.cryptotrackr.data.remote.dto.TrendingResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    @GET(ApiConstants.COINS_MARKETS)
    suspend fun getCoinsMarkets(
        @Query(ApiConstants.PARAM_VS_CURRENCY) vsCurrency: String = ApiConstants.DEFAULT_CURRENCY,
        @Query(ApiConstants.PARAM_ORDER) order: String = ApiConstants.DEFAULT_ORDER,
        @Query(ApiConstants.PARAM_PER_PAGE) perPage: Int = ApiConstants.DEFAULT_PER_PAGE,
        @Query(ApiConstants.PARAM_PAGE) page: Int = ApiConstants.DEFAULT_PAGE,
        @Query(ApiConstants.PARAM_SPARKLINE) sparkline: Boolean = true,
        @Query(ApiConstants.PARAM_PRICE_CHANGE_PERCENTAGE) priceChangePercentage: String = "1h,24h,7d"
    ): List<CoinDto>

    @GET(ApiConstants.COINS_MARKETS)
    suspend fun getCoinsByIds(
        @Query(ApiConstants.PARAM_IDS) ids: String,
        @Query(ApiConstants.PARAM_VS_CURRENCY) vsCurrency: String = ApiConstants.DEFAULT_CURRENCY,
        @Query(ApiConstants.PARAM_SPARKLINE) sparkline: Boolean = true,
        @Query(ApiConstants.PARAM_PRICE_CHANGE_PERCENTAGE) priceChangePercentage: String = "1h,24h,7d"
    ): List<CoinDto>

    @GET(ApiConstants.COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path("id") coinId: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = true,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
        @Query("sparkline") sparkline: Boolean = true
    ): CoinDetailDto

    @GET(ApiConstants.COIN_MARKET_CHART)
    suspend fun getMarketChart(
        @Path("id") coinId: String,
        @Query(ApiConstants.PARAM_VS_CURRENCY) vsCurrency: String = ApiConstants.DEFAULT_CURRENCY,
        @Query(ApiConstants.PARAM_DAYS) days: String = "1"
    ): MarketChartDto

    @GET(ApiConstants.SEARCH_COINS)
    suspend fun searchCoins(
        @Query(ApiConstants.PARAM_QUERY) query: String
    ): SearchResponseDto

    @GET(ApiConstants.TRENDING)
    suspend fun getTrendingCoins(): TrendingResponseDto
}
