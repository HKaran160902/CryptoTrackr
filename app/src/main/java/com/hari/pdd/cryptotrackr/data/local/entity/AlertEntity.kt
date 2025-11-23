package com.hari.pdd.cryptotrackr.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alerts")
data class AlertEntity(
    @PrimaryKey(autoGenerate = true)
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
)
