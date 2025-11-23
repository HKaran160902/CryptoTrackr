package com.hari.pdd.cryptotrackr.ui.screens.watchlist

import com.hari.pdd.cryptotrackr.domain.model.Coin

data class WatchlistUiState(
    val coins: List<Coin> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class WatchlistEvent {
    data object LoadWatchlist : WatchlistEvent()
    data class RemoveFromWatchlist(val coinId: String) : WatchlistEvent()
}
