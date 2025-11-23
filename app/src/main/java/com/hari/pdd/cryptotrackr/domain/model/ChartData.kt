package com.hari.pdd.cryptotrackr.domain.model

data class ChartData(
    val prices: List<PricePoint>,
    val marketCaps: List<Double>,
    val volumes: List<Double>
)

data class PricePoint(
    val timestamp: Long,
    val price: Double
)

enum class ChartPeriod(val days: String, val label: String) {
    DAY_1("1", "24H"),
    WEEK_1("7", "7D"),
    MONTH_1("30", "30D"),
    YEAR_1("365", "1Y")
}
