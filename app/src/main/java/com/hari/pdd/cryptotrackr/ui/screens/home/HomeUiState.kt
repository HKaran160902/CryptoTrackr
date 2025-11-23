package com.hari.pdd.cryptotrackr.ui.screens.home

import com.hari.pdd.cryptotrackr.domain.model.Coin

data class HomeUiState(
    val coins: List<Coin> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val searchResults: List<Coin> = emptyList()
)

sealed class HomeEvent {
    data object LoadCoins : HomeEvent()
    data object RefreshCoins : HomeEvent()
    data class Search(val query: String) : HomeEvent()
    data object ClearSearch : HomeEvent()
}
