package com.hari.pdd.cryptotrackr.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationAdd
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hari.pdd.cryptotrackr.domain.model.ChartPeriod
import com.hari.pdd.cryptotrackr.ui.components.buttons.CryptoPrimaryButton
import com.hari.pdd.cryptotrackr.ui.components.cards.CryptoCard
import com.hari.pdd.cryptotrackr.ui.components.charts.PriceLineChart
import com.hari.pdd.cryptotrackr.ui.components.chips.PriceChangeChip
import com.hari.pdd.cryptotrackr.ui.components.states.ErrorState
import com.hari.pdd.cryptotrackr.ui.components.states.ErrorStateSmall
import com.hari.pdd.cryptotrackr.ui.components.states.LoadingIndicator
import com.hari.pdd.cryptotrackr.ui.components.states.LoadingIndicatorSmall
import com.hari.pdd.cryptotrackr.ui.components.topbar.CryptoTopBar
import com.hari.pdd.cryptotrackr.ui.screens.detail.components.AddAlertDialog
import com.hari.pdd.cryptotrackr.ui.theme.Background
import com.hari.pdd.cryptotrackr.ui.theme.Primary
import com.hari.pdd.cryptotrackr.ui.theme.Secondary
import com.hari.pdd.cryptotrackr.ui.theme.Surface
import com.hari.pdd.cryptotrackr.ui.theme.TextPrimary
import com.hari.pdd.cryptotrackr.ui.theme.TextSecondary
import com.hari.pdd.cryptotrackr.util.formatMarketCap
import com.hari.pdd.cryptotrackr.util.formatPrice
import com.hari.pdd.cryptotrackr.util.formatSupply

@Composable
fun CoinDetailScreen(
    coinId: String,
    onBackClick: () -> Unit,
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        CryptoTopBar(
            title = uiState.coinDetail?.name ?: "Loading...",
            showBackButton = true,
            onBackClick = onBackClick,
            actions = {
                IconButton(onClick = { viewModel.onEvent(CoinDetailEvent.ToggleWatchlist) }) {
                    Icon(
                        imageVector = if (uiState.isInWatchlist) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Watchlist",
                        tint = Secondary
                    )
                }
                IconButton(onClick = { viewModel.onEvent(CoinDetailEvent.ShowAddAlertDialog) }) {
                    Icon(
                        imageVector = Icons.Default.NotificationAdd,
                        contentDescription = "Add Price Alert",
                        tint = Secondary
                    )
                }
            }
        )

        when {
            uiState.isLoading && uiState.coinDetail == null -> {
                LoadingIndicator()
            }

            uiState.error != null && uiState.coinDetail == null -> {
                ErrorState(
                    message = uiState.error ?: "Unknown error",
                    onRetry = { viewModel.onEvent(CoinDetailEvent.LoadCoinDetail) }
                )
            }

            uiState.coinDetail != null -> {
                val coin = uiState.coinDetail!!

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    // Header with icon, price, change
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = coin.image,
                            contentDescription = coin.name,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = coin.currentPrice.formatPrice(),
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = coin.symbol,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextSecondary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                PriceChangeChip(percentage = coin.priceChangePercentage24h)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Chart Period Selector
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ChartPeriod.entries.forEach { period ->
                            FilterChip(
                                selected = uiState.selectedPeriod == period,
                                onClick = { viewModel.onEvent(CoinDetailEvent.SelectPeriod(period)) },
                                label = { Text(period.label) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Secondary,
                                    selectedLabelColor = Primary,
                                    containerColor = Surface,
                                    labelColor = TextSecondary
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Price Chart
                    CryptoCard {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .padding(16.dp)
                        ) {
                            when {
                                uiState.isChartLoading -> {
                                    LoadingIndicatorSmall(modifier = Modifier.align(Alignment.Center))
                                }
                                uiState.chartError != null -> {
                                    ErrorStateSmall(
                                        message = uiState.chartError ?: "Failed to load chart",
                                        onRetry = { viewModel.onEvent(CoinDetailEvent.RetryChart) },
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                                uiState.chartData != null -> {
                                    PriceLineChart(
                                        pricePoints = uiState.chartData!!.prices,
                                        isPositive = coin.priceChangePercentage24h >= 0,
                                        periodDays = uiState.selectedPeriod.days,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Stats Grid
                    Text(
                        text = "Statistics",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    CryptoCard {
                        Column(modifier = Modifier.padding(16.dp)) {
                            StatRow("Market Cap Rank", "#${coin.marketCapRank}")
                            StatRow("Market Cap", "$${coin.marketCap.formatMarketCap()}")
                            StatRow("24h Volume", "$${coin.totalVolume.formatMarketCap()}")
                            StatRow("24h High", coin.high24h.formatPrice())
                            StatRow("24h Low", coin.low24h.formatPrice())
                            StatRow("Circulating Supply", coin.circulatingSupply.formatSupply())
                            coin.totalSupply?.let {
                                StatRow("Total Supply", it.formatSupply())
                            }
                            StatRow("All-Time High", coin.ath.formatPrice())
                            StatRow("All-Time Low", coin.atl.formatPrice())
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }

    // Add Alert Dialog
    if (uiState.showAddAlertDialog && uiState.coinDetail != null) {
        AddAlertDialog(
            coinName = uiState.coinDetail!!.name,
            currentPrice = uiState.coinDetail!!.currentPrice,
            onDismiss = { viewModel.onEvent(CoinDetailEvent.HideAddAlertDialog) },
            onConfirm = { targetPrice, isAbove ->
                viewModel.onEvent(CoinDetailEvent.AddPriceAlert(targetPrice, isAbove))
            }
        )
    }
}

@Composable
private fun StatRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
        )
    }
}
