package com.hari.pdd.cryptotrackr.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MarketChartDto(
    @SerializedName("prices")
    val prices: List<List<Double>>?,

    @SerializedName("market_caps")
    val marketCaps: List<List<Double>>?,

    @SerializedName("total_volumes")
    val totalVolumes: List<List<Double>>?
)
