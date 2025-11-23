package com.hari.pdd.cryptotrackr.di

import android.content.Context
import androidx.room.Room
import com.hari.pdd.cryptotrackr.data.local.database.CryptoDatabase
import com.hari.pdd.cryptotrackr.data.local.database.dao.AlertDao
import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao
import com.hari.pdd.cryptotrackr.data.local.database.dao.HoldingDao
import com.hari.pdd.cryptotrackr.data.local.database.dao.WatchlistDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CryptoDatabase {
        return Room.databaseBuilder(
            context,
            CryptoDatabase::class.java,
            CryptoDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinDao(database: CryptoDatabase): CoinDao {
        return database.coinDao()
    }

    @Provides
    @Singleton
    fun provideWatchlistDao(database: CryptoDatabase): WatchlistDao {
        return database.watchlistDao()
    }

    @Provides
    @Singleton
    fun provideHoldingDao(database: CryptoDatabase): HoldingDao {
        return database.holdingDao()
    }

    @Provides
    @Singleton
    fun provideAlertDao(database: CryptoDatabase): AlertDao {
        return database.alertDao()
    }
}
