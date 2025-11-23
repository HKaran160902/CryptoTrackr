package com.hari.pdd.cryptotrackr.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Price formatting
fun Double.formatPrice(currency: String = "usd"): String {
    val symbol = getCurrencySymbol(currency)
    return when {
        this >= 1000 -> "$symbol${formatWithCommas(this)}"
        this >= 1 -> "$symbol${String.format(Locale.US, "%.2f", this)}"
        this >= 0.01 -> "$symbol${String.format(Locale.US, "%.4f", this)}"
        else -> "$symbol${String.format(Locale.US, "%.8f", this)}"
    }
}

fun Double.formatPercentage(): String {
    val sign = if (this >= 0) "+" else ""
    return "$sign${String.format(Locale.US, "%.2f", this)}%"
}

fun Long.formatMarketCap(): String {
    return when {
        this >= 1_000_000_000_000 -> String.format(Locale.US, "%.2fT", this / 1_000_000_000_000.0)
        this >= 1_000_000_000 -> String.format(Locale.US, "%.2fB", this / 1_000_000_000.0)
        this >= 1_000_000 -> String.format(Locale.US, "%.2fM", this / 1_000_000.0)
        this >= 1_000 -> String.format(Locale.US, "%.2fK", this / 1_000.0)
        else -> this.toString()
    }
}

fun Double.formatSupply(): String {
    return when {
        this >= 1_000_000_000 -> String.format(Locale.US, "%.2fB", this / 1_000_000_000.0)
        this >= 1_000_000 -> String.format(Locale.US, "%.2fM", this / 1_000_000.0)
        this >= 1_000 -> String.format(Locale.US, "%.2fK", this / 1_000.0)
        else -> formatWithCommas(this)
    }
}

fun Double.formatQuantity(): String {
    return when {
        this >= 1 -> String.format(Locale.US, "%.4f", this)
        else -> String.format(Locale.US, "%.8f", this)
    }
}

private fun formatWithCommas(value: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale.US) as DecimalFormat
    formatter.applyPattern("#,##0.00")
    return formatter.format(value)
}

fun getCurrencySymbol(currency: String): String {
    return when (currency.lowercase()) {
        "usd" -> "$"
        "eur" -> "€"
        "gbp" -> "£"
        "inr" -> "₹"
        "jpy" -> "¥"
        "aud", "cad" -> "$"
        "chf" -> "CHF "
        else -> "$"
    }
}

fun Long.formatMarketCapWithCurrency(currency: String): String {
    val symbol = getCurrencySymbol(currency)
    return when {
        this >= 1_000_000_000_000 -> "$symbol${String.format(Locale.US, "%.2fT", this / 1_000_000_000_000.0)}"
        this >= 1_000_000_000 -> "$symbol${String.format(Locale.US, "%.2fB", this / 1_000_000_000.0)}"
        this >= 1_000_000 -> "$symbol${String.format(Locale.US, "%.2fM", this / 1_000_000.0)}"
        this >= 1_000 -> "$symbol${String.format(Locale.US, "%.2fK", this / 1_000.0)}"
        else -> "$symbol$this"
    }
}

// Date formatting
fun Long.formatDate(): String {
    val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    return sdf.format(Date(this))
}

fun Long.formatDateTime(): String {
    val sdf = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.US)
    return sdf.format(Date(this))
}

fun Long.formatTime(): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.US)
    return sdf.format(Date(this))
}

fun String?.parseDate(): Long? {
    if (this == null) return null
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        sdf.parse(this)?.time
    } catch (e: Exception) {
        null
    }
}

// Time ago
fun Long.timeAgo(): String {
    val now = System.currentTimeMillis()
    val diff = now - this

    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    return when {
        days > 0 -> "$days day${if (days > 1) "s" else ""} ago"
        hours > 0 -> "$hours hour${if (hours > 1) "s" else ""} ago"
        minutes > 0 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
        else -> "Just now"
    }
}

// Chart formatting
fun Long.formatChartTime(periodDays: String): String {
    val pattern = when (periodDays) {
        "1" -> "HH:mm"      // 24H - show hours:minutes
        "7" -> "EEE"        // 7D - show day name
        "30" -> "dd MMM"    // 30D - show date and month
        "365" -> "MMM"      // 1Y - show month
        else -> "HH:mm"
    }
    val sdf = SimpleDateFormat(pattern, Locale.US)
    return sdf.format(Date(this))
}

fun Double.formatAxisPrice(): String {
    return when {
        this >= 10_000_000 -> String.format(Locale.US, "%.1fM", this / 1_000_000.0)
        this >= 1_000_000 -> String.format(Locale.US, "%.2fM", this / 1_000_000.0)
        this >= 100_000 -> String.format(Locale.US, "%.0fK", this / 1_000.0)
        this >= 10_000 -> String.format(Locale.US, "%.1fK", this / 1_000.0)
        this >= 1_000 -> String.format(Locale.US, "%.2fK", this / 1_000.0)
        this >= 100 -> String.format(Locale.US, "%.0f", this)
        this >= 1 -> String.format(Locale.US, "%.2f", this)
        this >= 0.01 -> String.format(Locale.US, "%.4f", this)
        else -> String.format(Locale.US, "%.6f", this)
    }
}
