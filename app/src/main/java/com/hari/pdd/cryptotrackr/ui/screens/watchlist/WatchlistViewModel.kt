package com.hari.pdd.cryptotrackr.ui.screens.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hari.pdd.cryptotrackr.domain.repository.WatchlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val watchlistRepository: WatchlistRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WatchlistUiState())
    val uiState: StateFlow<WatchlistUiState> = _uiState.asStateFlow()

    init {
        loadWatchlist()
    }

    fun onEvent(event: WatchlistEvent) {
        when (event) {
            is WatchlistEvent.LoadWatchlist -> loadWatchlist()
            is WatchlistEvent.RemoveFromWatchlist -> removeFromWatchlist(event.coinId)
        }
    }

    private fun loadWatchlist() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            watchlistRepository.getWatchlistCoins().collect { coins ->
                _uiState.update {
                    it.copy(
                        coins = coins,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    private fun removeFromWatchlist(coinId: String) {
        viewModelScope.launch {
            watchlistRepository.removeFromWatchlist(coinId)
        }
    }
}
