package com.hari.pdd.cryptotrackr.domain.model

data class Holding(
    val id: Long = 0,
    val coinId: String,
    val coinName: String,
    val coinSymbol: String,
    val coinImage: String,
    val quantity: Double,
    val purchasePrice: Double,
    val purchaseDate: Long = System.currentTimeMillis(),
    val notes: String? = null
) {
    val totalInvested: Double
        get() = quantity * purchasePrice

    fun getCurrentValue(currentPrice: Double): Double = quantity * currentPrice

    fun getProfitLoss(currentPrice: Double): Double = getCurrentValue(currentPrice) - totalInvested

    fun getProfitLossPercentage(currentPrice: Double): Double {
        if (totalInvested == 0.0) return 0.0
        return ((getCurrentValue(currentPrice) - totalInvested) / totalInvested) * 100
    }
}

data class PortfolioSummary(
    val totalInvested: Double,
    val currentValue: Double,
    val totalHoldings: Int,
    val uniqueCoins: Int
) {
    val profitLoss: Double
        get() = currentValue - totalInvested

    val profitLossPercentage: Double
        get() = if (totalInvested == 0.0) 0.0 else ((currentValue - totalInvested) / totalInvested) * 100

    val isProfit: Boolean
        get() = profitLoss >= 0
}
