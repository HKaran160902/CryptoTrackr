package com.hari.pdd.cryptotrackr.domain.model

data class PriceAlert(
    val id: Long = 0,
    val coinId: String,
    val coinName: String,
    val coinSymbol: String,
    val coinImage: String,
    val targetPrice: Double,
    val isAbove: Boolean, // true = alert when price goes above, false = when below
    val isEnabled: Boolean = true,
    val isTriggered: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val triggeredAt: Long? = null
) {
    fun shouldTrigger(currentPrice: Double): Boolean {
        if (!isEnabled || isTriggered) return false
        return if (isAbove) {
            currentPrice >= targetPrice
        } else {
            currentPrice <= targetPrice
        }
    }

    val conditionText: String
        get() = if (isAbove) "Above" else "Below"
}
