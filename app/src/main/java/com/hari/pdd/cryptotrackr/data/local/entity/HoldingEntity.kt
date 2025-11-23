package com.hari.pdd.cryptotrackr.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "holdings")
data class HoldingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val coinId: String,
    val coinName: String,
    val coinSymbol: String,
    val coinImage: String,
    val quantity: Double,
    val purchasePrice: Double,
    val purchaseDate: Long = System.currentTimeMillis(),
    val notes: String? = null
)
