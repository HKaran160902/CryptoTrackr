package com.hari.pdd.cryptotrackr.data.repository

import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao
import com.hari.pdd.cryptotrackr.data.local.database.dao.HoldingDao
import com.hari.pdd.cryptotrackr.data.mapper.CoinMapper.toDomain
import com.hari.pdd.cryptotrackr.data.mapper.CoinMapper.toEntity
import com.hari.pdd.cryptotrackr.data.remote.api.CoinGeckoApi
import com.hari.pdd.cryptotrackr.domain.model.Holding
import com.hari.pdd.cryptotrackr.domain.model.PortfolioSummary
import com.hari.pdd.cryptotrackr.domain.repository.HoldingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HoldingRepositoryImpl @Inject constructor(
    private val holdingDao: HoldingDao,
    private val coinDao: CoinDao,
    private val api: CoinGeckoApi
) : HoldingRepository {

    override fun getAllHoldings(): Flow<List<Holding>> {
        return holdingDao.getAllHoldings().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getHoldingsWithCurrentPrices(): Flow<List<Pair<Holding, Double>>> {
        return holdingDao.getAllHoldings().flatMapLatest { holdings ->
            if (holdings.isEmpty()) {
                flow { emit(emptyList()) }
            } else {
                val coinIds = holdings.map { it.coinId }.distinct()

                // Get current prices
                coinDao.getCoinsByIds(coinIds).map { coins ->
                    val priceMap = coins.associate { it.id to (it.currentPrice ?: 0.0) }

                    holdings.map { holding ->
                        val currentPrice = priceMap[holding.coinId] ?: holding.purchasePrice
                        holding.toDomain() to currentPrice
                    }
                }
            }
        }
    }

    override suspend fun getHoldingById(holdingId: Long): Holding? {
        return holdingDao.getHoldingById(holdingId)?.toDomain()
    }

    override fun getHoldingsByCoinId(coinId: String): Flow<List<Holding>> {
        return holdingDao.getHoldingsByCoinId(coinId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addHolding(holding: Holding): Long {
        return holdingDao.insertHolding(holding.toEntity())
    }

    override suspend fun updateHolding(holding: Holding) {
        holdingDao.updateHolding(holding.toEntity())
    }

    override suspend fun deleteHolding(holding: Holding) {
        holdingDao.deleteHolding(holding.toEntity())
    }

    override suspend fun deleteHoldingById(holdingId: Long) {
        holdingDao.deleteHoldingById(holdingId)
    }

    override suspend fun clearAllHoldings() {
        holdingDao.clearAllHoldings()
    }

    override fun getTotalInvested(): Flow<Double> {
        return holdingDao.getTotalInvested().map { it ?: 0.0 }
    }

    override fun getPortfolioSummary(): Flow<PortfolioSummary> {
        return getHoldingsWithCurrentPrices().map { holdingsWithPrices ->
            if (holdingsWithPrices.isEmpty()) {
                PortfolioSummary(
                    totalInvested = 0.0,
                    currentValue = 0.0,
                    totalHoldings = 0,
                    uniqueCoins = 0
                )
            } else {
                val totalInvested = holdingsWithPrices.sumOf { (holding, _) ->
                    holding.totalInvested
                }

                val currentValue = holdingsWithPrices.sumOf { (holding, currentPrice) ->
                    holding.getCurrentValue(currentPrice)
                }

                val uniqueCoins = holdingsWithPrices.map { it.first.coinId }.distinct().size

                PortfolioSummary(
                    totalInvested = totalInvested,
                    currentValue = currentValue,
                    totalHoldings = holdingsWithPrices.size,
                    uniqueCoins = uniqueCoins
                )
            }
        }
    }
}
