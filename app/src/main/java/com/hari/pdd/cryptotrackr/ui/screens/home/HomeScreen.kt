package com.hari.pdd.cryptotrackr.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hari.pdd.cryptotrackr.ui.components.cards.CoinListItem
import com.hari.pdd.cryptotrackr.ui.components.inputs.CryptoSearchBar
import com.hari.pdd.cryptotrackr.ui.components.states.ErrorState
import com.hari.pdd.cryptotrackr.ui.components.states.LoadingIndicator
import com.hari.pdd.cryptotrackr.ui.components.topbar.CryptoTopBar
import com.hari.pdd.cryptotrackr.ui.theme.Background
import com.hari.pdd.cryptotrackr.ui.theme.Secondary
import com.hari.pdd.cryptotrackr.ui.theme.SurfaceVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCoinClick: (String) -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CryptoTopBar(
            title = "CryptoTrackr",
            actions = {
                IconButton(onClick = onSettingsClick) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = Secondary
                    )
                }
            }
        )

        // Search Bar
        CryptoSearchBar(
            query = uiState.searchQuery,
            onQueryChange = { viewModel.onEvent(HomeEvent.Search(it)) },
            onSearch = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Content
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                uiState.isLoading && uiState.coins.isEmpty() -> {
                    LoadingIndicator()
                }

                uiState.error != null && uiState.coins.isEmpty() -> {
                    ErrorState(
                        message = uiState.error ?: "Unknown error",
                        onRetry = { viewModel.onEvent(HomeEvent.LoadCoins) }
                    )
                }

                else -> {
                    val displayCoins = if (uiState.searchQuery.isNotBlank()) {
                        uiState.searchResults
                    } else {
                        uiState.coins
                    }

                    val pullToRefreshState = rememberPullToRefreshState()

                    LaunchedEffect(uiState.isRefreshing) {
                        if (!uiState.isRefreshing) {
                            pullToRefreshState.endRefresh()
                        }
                    }

                    LaunchedEffect(pullToRefreshState.isRefreshing) {
                        if (pullToRefreshState.isRefreshing) {
                            viewModel.onEvent(HomeEvent.RefreshCoins)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(pullToRefreshState.nestedScrollConnection)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(
                                items = displayCoins,
                                key = { it.id }
                            ) { coin ->
                                CoinListItem(
                                    coin = coin,
                                    onClick = { onCoinClick(coin.id) }
                                )
                                HorizontalDivider(
                                    color = SurfaceVariant,
                                    thickness = 0.5.dp
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }

                        if (pullToRefreshState.isRefreshing || pullToRefreshState.progress > 0f) {
                            PullToRefreshContainer(
                                state = pullToRefreshState,
                                modifier = Modifier.align(Alignment.TopCenter)
                            )
                        }
                    }
                }
            }
        }
    }
}
