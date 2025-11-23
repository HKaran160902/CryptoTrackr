package com.hari.pdd.cryptotrackr.util

object Constants {

    // Cache duration in milliseconds
    const val CACHE_DURATION_MS = 5 * 60 * 1000L // 5 minutes

    // Work Manager
    const val PRICE_ALERT_WORK_NAME = "price_alert_work"
    const val PRICE_ALERT_INTERVAL_MINUTES = 15L

    // Notification
    const val NOTIFICATION_ID_PRICE_ALERT = 1001

    // Currencies
    val SUPPORTED_CURRENCIES = listOf(
        "usd" to "US Dollar ($)",
        "eur" to "Euro (€)",
        "gbp" to "British Pound (£)",
        "inr" to "Indian Rupee (₹)",
        "jpy" to "Japanese Yen (¥)",
        "aud" to "Australian Dollar (A$)",
        "cad" to "Canadian Dollar (C$)",
        "chf" to "Swiss Franc (CHF)"
    )

    // Avatars
    val AVATAR_OPTIONS = listOf(
        "avatar_bitcoin",
        "avatar_ethereum",
        "avatar_bull",
        "avatar_bear",
        "avatar_rocket",
        "avatar_diamond",
        "avatar_chart",
        "avatar_default"
    )
}
