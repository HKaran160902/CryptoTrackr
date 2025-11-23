package com.hari.pdd.cryptotrackr.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double?,
    val marketCap: Long?,
    val marketCapRank: Int?,
    val totalVolume: Long?,
    val high24h: Double?,
    val low24h: Double?,
    val priceChange24h: Double?,
    val priceChangePercentage24h: Double?,
    val priceChangePercentage1h: Double?,
    val priceChangePercentage7d: Double?,
    val circulatingSupply: Double?,
    val totalSupply: Double?,
    val maxSupply: Double?,
    val ath: Double?,
    val athChangePercentage: Double?,
    val athDate: String?,
    val atl: Double?,
    val atlChangePercentage: Double?,
    val atlDate: String?,
    val lastUpdated: String?,
    val sparklineData: String?, // JSON string of price list
    val cachedAt: Long = System.currentTimeMillis()
)
