package com.hari.pdd.cryptotrackr.di

import com.hari.pdd.cryptotrackr.data.repository.AlertRepositoryImpl
import com.hari.pdd.cryptotrackr.data.repository.CoinRepositoryImpl
import com.hari.pdd.cryptotrackr.data.repository.HoldingRepositoryImpl
import com.hari.pdd.cryptotrackr.data.repository.WatchlistRepositoryImpl
import com.hari.pdd.cryptotrackr.domain.repository.AlertRepository
import com.hari.pdd.cryptotrackr.domain.repository.CoinRepository
import com.hari.pdd.cryptotrackr.domain.repository.HoldingRepository
import com.hari.pdd.cryptotrackr.domain.repository.WatchlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoinRepository(
        coinRepositoryImpl: CoinRepositoryImpl
    ): CoinRepository

    @Binds
    @Singleton
    abstract fun bindWatchlistRepository(
        watchlistRepositoryImpl: WatchlistRepositoryImpl
    ): WatchlistRepository

    @Binds
    @Singleton
    abstract fun bindHoldingRepository(
        holdingRepositoryImpl: HoldingRepositoryImpl
    ): HoldingRepository

    @Binds
    @Singleton
    abstract fun bindAlertRepository(
        alertRepositoryImpl: AlertRepositoryImpl
    ): AlertRepository
}
