package com.hari.pdd.cryptotrackr.data.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hari.pdd.cryptotrackr.data.local.entity.AlertEntity
import com.hari.pdd.cryptotrackr.data.local.entity.CoinEntity
import com.hari.pdd.cryptotrackr.data.local.entity.HoldingEntity
import com.hari.pdd.cryptotrackr.data.remote.dto.CoinDetailDto
import com.hari.pdd.cryptotrackr.data.remote.dto.CoinDto
import com.hari.pdd.cryptotrackr.data.remote.dto.MarketChartDto
import com.hari.pdd.cryptotrackr.domain.model.ChartData
import com.hari.pdd.cryptotrackr.domain.model.Coin
import com.hari.pdd.cryptotrackr.domain.model.CoinDetail
import com.hari.pdd.cryptotrackr.domain.model.Holding
import com.hari.pdd.cryptotrackr.domain.model.PriceAlert
import com.hari.pdd.cryptotrackr.domain.model.PricePoint

object CoinMapper {

    private val gson = Gson()

    // DTO to Domain
    fun CoinDto.toDomain(): Coin {
        return Coin(
            id = id,
            symbol = symbol.uppercase(),
            name = name,
            image = image,
            currentPrice = currentPrice ?: 0.0,
            marketCap = marketCap ?: 0,
            marketCapRank = marketCapRank ?: 0,
            totalVolume = totalVolume ?: 0,
            high24h = high24h ?: 0.0,
            low24h = low24h ?: 0.0,
            priceChange24h = priceChange24h ?: 0.0,
            priceChangePercentage24h = priceChangePercentage24h ?: 0.0,
            priceChangePercentage1h = priceChangePercentage1h ?: 0.0,
            priceChangePercentage7d = priceChangePercentage7d ?: 0.0,
            circulatingSupply = circulatingSupply ?: 0.0,
            totalSupply = totalSupply,
            maxSupply = maxSupply,
            ath = ath ?: 0.0,
            athChangePercentage = athChangePercentage ?: 0.0,
            athDate = athDate,
            atl = atl ?: 0.0,
            atlChangePercentage = atlChangePercentage ?: 0.0,
            atlDate = atlDate,
            lastUpdated = lastUpdated,
            sparklineData = sparklineIn7d?.price ?: emptyList()
        )
    }

    fun CoinDetailDto.toDomain(currency: String = "usd"): CoinDetail {
        return CoinDetail(
            id = id,
            symbol = symbol.uppercase(),
            name = name,
            description = description?.en ?: "",
            image = image?.large ?: image?.small ?: "",
            marketCapRank = marketCapRank ?: 0,
            currentPrice = marketData?.currentPrice?.get(currency) ?: 0.0,
            marketCap = marketData?.marketCap?.get(currency) ?: 0,
            totalVolume = marketData?.totalVolume?.get(currency) ?: 0,
            high24h = marketData?.high24h?.get(currency) ?: 0.0,
            low24h = marketData?.low24h?.get(currency) ?: 0.0,
            priceChange24h = marketData?.priceChange24h ?: 0.0,
            priceChangePercentage24h = marketData?.priceChangePercentage24h ?: 0.0,
            priceChangePercentage7d = marketData?.priceChangePercentage7d ?: 0.0,
            priceChangePercentage30d = marketData?.priceChangePercentage30d ?: 0.0,
            priceChangePercentage1y = marketData?.priceChangePercentage1y ?: 0.0,
            circulatingSupply = marketData?.circulatingSupply ?: 0.0,
            totalSupply = marketData?.totalSupply,
            maxSupply = marketData?.maxSupply,
            ath = marketData?.ath?.get(currency) ?: 0.0,
            athChangePercentage = marketData?.athChangePercentage?.get(currency) ?: 0.0,
            athDate = marketData?.athDate?.get(currency),
            atl = marketData?.atl?.get(currency) ?: 0.0,
            atlChangePercentage = marketData?.atlChangePercentage?.get(currency) ?: 0.0,
            atlDate = marketData?.atlDate?.get(currency),
            lastUpdated = lastUpdated,
            sparklineData = marketData?.sparkline7d?.price ?: emptyList()
        )
    }

    fun MarketChartDto.toDomain(): ChartData {
        val pricePoints = prices?.map { point ->
            PricePoint(
                timestamp = point[0].toLong(),
                price = point[1]
            )
        } ?: emptyList()

        return ChartData(
            prices = pricePoints,
            marketCaps = marketCaps?.map { it[1] } ?: emptyList(),
            volumes = totalVolumes?.map { it[1] } ?: emptyList()
        )
    }

    // DTO to Entity
    fun CoinDto.toEntity(): CoinEntity {
        return CoinEntity(
            id = id,
            symbol = symbol,
            name = name,
            image = image,
            currentPrice = currentPrice,
            marketCap = marketCap,
            marketCapRank = marketCapRank,
            totalVolume = totalVolume,
            high24h = high24h,
            low24h = low24h,
            priceChange24h = priceChange24h,
            priceChangePercentage24h = priceChangePercentage24h,
            priceChangePercentage1h = priceChangePercentage1h,
            priceChangePercentage7d = priceChangePercentage7d,
            circulatingSupply = circulatingSupply,
            totalSupply = totalSupply,
            maxSupply = maxSupply,
            ath = ath,
            athChangePercentage = athChangePercentage,
            athDate = athDate,
            atl = atl,
            atlChangePercentage = atlChangePercentage,
            atlDate = atlDate,
            lastUpdated = lastUpdated,
            sparklineData = sparklineIn7d?.price?.let { gson.toJson(it) }
        )
    }

    // Entity to Domain
    fun CoinEntity.toDomain(): Coin {
        val sparklineList: List<Double> = sparklineData?.let {
            try {
                val type = object : TypeToken<List<Double>>() {}.type
                gson.fromJson(it, type)
            } catch (e: Exception) {
                emptyList()
            }
        } ?: emptyList()

        return Coin(
            id = id,
            symbol = symbol.uppercase(),
            name = name,
            image = image,
            currentPrice = currentPrice ?: 0.0,
            marketCap = marketCap ?: 0,
            marketCapRank = marketCapRank ?: 0,
            totalVolume = totalVolume ?: 0,
            high24h = high24h ?: 0.0,
            low24h = low24h ?: 0.0,
            priceChange24h = priceChange24h ?: 0.0,
            priceChangePercentage24h = priceChangePercentage24h ?: 0.0,
            priceChangePercentage1h = priceChangePercentage1h ?: 0.0,
            priceChangePercentage7d = priceChangePercentage7d ?: 0.0,
            circulatingSupply = circulatingSupply ?: 0.0,
            totalSupply = totalSupply,
            maxSupply = maxSupply,
            ath = ath ?: 0.0,
            athChangePercentage = athChangePercentage ?: 0.0,
            athDate = athDate,
            atl = atl ?: 0.0,
            atlChangePercentage = atlChangePercentage ?: 0.0,
            atlDate = atlDate,
            lastUpdated = lastUpdated,
            sparklineData = sparklineList
        )
    }

    // Holding Entity to Domain
    fun HoldingEntity.toDomain(): Holding {
        return Holding(
            id = id,
            coinId = coinId,
            coinName = coinName,
            coinSymbol = coinSymbol,
            coinImage = coinImage,
            quantity = quantity,
            purchasePrice = purchasePrice,
            purchaseDate = purchaseDate,
            notes = notes
        )
    }

    fun Holding.toEntity(): HoldingEntity {
        return HoldingEntity(
            id = id,
            coinId = coinId,
            coinName = coinName,
            coinSymbol = coinSymbol,
            coinImage = coinImage,
            quantity = quantity,
            purchasePrice = purchasePrice,
            purchaseDate = purchaseDate,
            notes = notes
        )
    }

    // Alert Entity to Domain
    fun AlertEntity.toDomain(): PriceAlert {
        return PriceAlert(
            id = id,
            coinId = coinId,
            coinName = coinName,
            coinSymbol = coinSymbol,
            coinImage = coinImage,
            targetPrice = targetPrice,
            isAbove = isAbove,
            isEnabled = isEnabled,
            isTriggered = isTriggered,
            createdAt = createdAt,
            triggeredAt = triggeredAt
        )
    }

    fun PriceAlert.toEntity(): AlertEntity {
        return AlertEntity(
            id = id,
            coinId = coinId,
            coinName = coinName,
            coinSymbol = coinSymbol,
            coinImage = coinImage,
            targetPrice = targetPrice,
            isAbove = isAbove,
            isEnabled = isEnabled,
            isTriggered = isTriggered,
            createdAt = createdAt,
            triggeredAt = triggeredAt
        )
    }
}
