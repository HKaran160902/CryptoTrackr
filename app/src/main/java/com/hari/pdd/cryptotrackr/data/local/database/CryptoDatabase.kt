package com.hari.pdd.cryptotrackr.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hari.pdd.cryptotrackr.data.local.database.dao.AlertDao
import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao
import com.hari.pdd.cryptotrackr.data.local.database.dao.HoldingDao
import com.hari.pdd.cryptotrackr.data.local.database.dao.WatchlistDao
import com.hari.pdd.cryptotrackr.data.local.entity.AlertEntity
import com.hari.pdd.cryptotrackr.data.local.entity.CoinEntity
import com.hari.pdd.cryptotrackr.data.local.entity.HoldingEntity
import com.hari.pdd.cryptotrackr.data.local.entity.WatchlistEntity

@Database(
    entities = [
        CoinEntity::class,
        WatchlistEntity::class,
        HoldingEntity::class,
        AlertEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao
    abstract fun watchlistDao(): WatchlistDao
    abstract fun holdingDao(): HoldingDao
    abstract fun alertDao(): AlertDao

    companion object {
        const val DATABASE_NAME = "crypto_trackr_db"
    }
}
