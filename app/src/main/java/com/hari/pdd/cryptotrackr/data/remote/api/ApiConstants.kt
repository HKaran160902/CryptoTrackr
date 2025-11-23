package com.hari.pdd.cryptotrackr.data.remote.api

object ApiConstants {
    const val BASE_URL = "https://api.coingecko.com/api/v3/"

    // Endpoints
    const val COINS_MARKETS = "coins/markets"
    const val COIN_DETAIL = "coins/{id}"
    const val COIN_MARKET_CHART = "coins/{id}/market_chart"
    const val SEARCH_COINS = "search"
    const val TRENDING = "search/trending"

    // Query Parameters
    const val PARAM_VS_CURRENCY = "vs_currency"
    const val PARAM_ORDER = "order"
    const val PARAM_PER_PAGE = "per_page"
    const val PARAM_PAGE = "page"
    const val PARAM_SPARKLINE = "sparkline"
    const val PARAM_PRICE_CHANGE_PERCENTAGE = "price_change_percentage"
    const val PARAM_IDS = "ids"
    const val PARAM_DAYS = "days"
    const val PARAM_QUERY = "query"

    // Default Values
    const val DEFAULT_CURRENCY = "usd"
    const val DEFAULT_ORDER = "market_cap_desc"
    const val DEFAULT_PER_PAGE = 50
    const val DEFAULT_PAGE = 1
}
