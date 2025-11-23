package com.hari.pdd.cryptotrackr.ui.screens.watchlist

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hari.pdd.cryptotrackr.ui.components.cards.CoinListItem
import com.hari.pdd.cryptotrackr.ui.components.states.EmptyWatchlistState
import com.hari.pdd.cryptotrackr.ui.components.states.LoadingIndicator
import com.hari.pdd.cryptotrackr.ui.components.topbar.CryptoTopBar
import com.hari.pdd.cryptotrackr.ui.theme.Background
import com.hari.pdd.cryptotrackr.ui.theme.Error
import com.hari.pdd.cryptotrackr.ui.theme.SurfaceVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistScreen(
    onCoinClick: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: WatchlistViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        CryptoTopBar(title = "Watchlist")

        when {
            uiState.isLoading -> {
                LoadingIndicator()
            }

            uiState.coins.isEmpty() -> {
                EmptyWatchlistState(onAction = onNavigateToHome)
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = uiState.coins,
                        key = { it.id }
                    ) { coin ->
                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = { value ->
                                if (value == SwipeToDismissBoxValue.EndToStart) {
                                    viewModel.onEvent(WatchlistEvent.RemoveFromWatchlist(coin.id))
                                    true
                                } else {
                                    false
                                }
                            }
                        )

                        SwipeToDismissBox(
                            state = dismissState,
                            backgroundContent = {
                                val color by animateColorAsState(
                                    when (dismissState.targetValue) {
                                        SwipeToDismissBoxValue.EndToStart -> Error
                                        else -> Color.Transparent
                                    },
                                    label = "color"
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color)
                                        .padding(horizontal = 20.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.White
                                    )
                                }
                            },
                            enableDismissFromStartToEnd = false
                        ) {
                            CoinListItem(
                                coin = coin,
                                onClick = { onCoinClick(coin.id) }
                            )
                        }

                        HorizontalDivider(
                            color = SurfaceVariant,
                            thickness = 0.5.dp
                        )
                    }
                }
            }
        }
    }
}
