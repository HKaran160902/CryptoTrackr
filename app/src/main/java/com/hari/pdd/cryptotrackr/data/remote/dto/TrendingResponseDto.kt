package com.hari.pdd.cryptotrackr.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrendingResponseDto(
    @SerializedName("coins")
    val coins: List<TrendingCoinItemDto>?
)

data class TrendingCoinItemDto(
    @SerializedName("item")
    val item: TrendingCoinDto
)

data class TrendingCoinDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("coin_id")
    val coinId: Int?,

    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("market_cap_rank")
    val marketCapRank: Int?,

    @SerializedName("thumb")
    val thumb: String?,

    @SerializedName("small")
    val small: String?,

    @SerializedName("large")
    val large: String?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("price_btc")
    val priceBtc: Double?,

    @SerializedName("score")
    val score: Int?
)
