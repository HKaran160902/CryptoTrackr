package com.hari.pdd.cryptotrackr.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: DescriptionDto?,

    @SerializedName("image")
    val image: ImageDto?,

    @SerializedName("market_cap_rank")
    val marketCapRank: Int?,

    @SerializedName("market_data")
    val marketData: MarketDataDto?,

    @SerializedName("last_updated")
    val lastUpdated: String?
)

data class DescriptionDto(
    @SerializedName("en")
    val en: String?
)

data class ImageDto(
    @SerializedName("thumb")
    val thumb: String?,

    @SerializedName("small")
    val small: String?,

    @SerializedName("large")
    val large: String?
)

data class MarketDataDto(
    @SerializedName("current_price")
    val currentPrice: Map<String, Double>?,

    @SerializedName("market_cap")
    val marketCap: Map<String, Long>?,

    @SerializedName("total_volume")
    val totalVolume: Map<String, Long>?,

    @SerializedName("high_24h")
    val high24h: Map<String, Double>?,

    @SerializedName("low_24h")
    val low24h: Map<String, Double>?,

    @SerializedName("price_change_24h")
    val priceChange24h: Double?,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double?,

    @SerializedName("price_change_percentage_7d")
    val priceChangePercentage7d: Double?,

    @SerializedName("price_change_percentage_30d")
    val priceChangePercentage30d: Double?,

    @SerializedName("price_change_percentage_1y")
    val priceChangePercentage1y: Double?,

    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double?,

    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double?,

    @SerializedName("circulating_supply")
    val circulatingSupply: Double?,

    @SerializedName("total_supply")
    val totalSupply: Double?,

    @SerializedName("max_supply")
    val maxSupply: Double?,

    @SerializedName("ath")
    val ath: Map<String, Double>?,

    @SerializedName("ath_change_percentage")
    val athChangePercentage: Map<String, Double>?,

    @SerializedName("ath_date")
    val athDate: Map<String, String>?,

    @SerializedName("atl")
    val atl: Map<String, Double>?,

    @SerializedName("atl_change_percentage")
    val atlChangePercentage: Map<String, Double>?,

    @SerializedName("atl_date")
    val atlDate: Map<String, String>?,

    @SerializedName("sparkline_7d")
    val sparkline7d: SparklineDto?
)
