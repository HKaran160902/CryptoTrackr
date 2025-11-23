package com.hari.pdd.cryptotrackr.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hari.pdd.cryptotrackr.domain.repository.CoinRepository
import com.hari.pdd.cryptotrackr.domain.repository.WatchlistRepository
import com.hari.pdd.cryptotrackr.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    private val watchlistRepository: WatchlistRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadCoins()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadCoins -> loadCoins()
            is HomeEvent.RefreshCoins -> refreshCoins()
            is HomeEvent.Search -> search(event.query)
            is HomeEvent.ClearSearch -> clearSearch()
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            combine(
                coinRepository.getCoins(),
                watchlistRepository.getWatchlistCoinIds()
            ) { coinsResource, watchlistIds ->
                when (coinsResource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        val coinsWithWatchlist = coinsResource.data?.map { coin ->
                            coin.copy(isInWatchlist = coin.id in watchlistIds)
                        } ?: emptyList()
                        _uiState.update {
                            it.copy(
                                coins = coinsWithWatchlist,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = coinsResource.message
                            )
                        }
                    }
                }
            }.collect { }
        }
    }

    private fun refreshCoins() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }

            combine(
                coinRepository.getCoins(forceRefresh = true),
                watchlistRepository.getWatchlistCoinIds()
            ) { coinsResource, watchlistIds ->
                when (coinsResource) {
                    is Resource.Success -> {
                        val coinsWithWatchlist = coinsResource.data?.map { coin ->
                            coin.copy(isInWatchlist = coin.id in watchlistIds)
                        } ?: emptyList()
                        _uiState.update {
                            it.copy(
                                coins = coinsWithWatchlist,
                                isRefreshing = false,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isRefreshing = false,
                                error = coinsResource.message
                            )
                        }
                    }
                    is Resource.Loading -> { }
                }
            }.collect { }
        }
    }

    private fun search(query: String) {
        _uiState.update { it.copy(searchQuery = query) }

        if (query.isBlank()) {
            _uiState.update { it.copy(isSearching = false, searchResults = emptyList()) }
            return
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300) // Debounce

            _uiState.update { it.copy(isSearching = true) }

            // Filter local results first
            val localResults = _uiState.value.coins.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.symbol.contains(query, ignoreCase = true)
            }

            _uiState.update { it.copy(searchResults = localResults, isSearching = false) }
        }
    }

    private fun clearSearch() {
        searchJob?.cancel()
        _uiState.update {
            it.copy(
                searchQuery = "",
                isSearching = false,
                searchResults = emptyList()
            )
        }
    }
}
