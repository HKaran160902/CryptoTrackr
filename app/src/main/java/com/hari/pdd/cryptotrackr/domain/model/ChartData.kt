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
    DAY_1("1", "1D"),
    WEEK_1("7", "1W"),
    MONTH_1("30", "1M"),
    MONTH_3("90", "3M"),
    MONTH_6("180", "6M"),
    YEAR_1("365", "1Y"),
    YEAR_5("1825", "5Y")
}
