package com.hari.pdd.cryptotrackr.domain.repository

import com.hari.pdd.cryptotrackr.domain.model.Holding
import com.hari.pdd.cryptotrackr.domain.model.PortfolioSummary
import kotlinx.coroutines.flow.Flow

interface HoldingRepository {

    fun getAllHoldings(): Flow<List<Holding>>

    fun getHoldingsWithCurrentPrices(): Flow<List<Pair<Holding, Double>>>

    suspend fun getHoldingById(holdingId: Long): Holding?

    fun getHoldingsByCoinId(coinId: String): Flow<List<Holding>>

    suspend fun addHolding(holding: Holding): Long

    suspend fun updateHolding(holding: Holding)

    suspend fun deleteHolding(holding: Holding)

    suspend fun deleteHoldingById(holdingId: Long)

    suspend fun clearAllHoldings()

    fun getTotalInvested(): Flow<Double>

    fun getPortfolioSummary(): Flow<PortfolioSummary>
}
