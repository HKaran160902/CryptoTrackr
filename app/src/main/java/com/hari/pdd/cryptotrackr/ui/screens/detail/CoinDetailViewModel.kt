package com.hari.pdd.cryptotrackr.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hari.pdd.cryptotrackr.domain.model.ChartPeriod
import com.hari.pdd.cryptotrackr.domain.model.PriceAlert
import com.hari.pdd.cryptotrackr.domain.repository.AlertRepository
import com.hari.pdd.cryptotrackr.domain.repository.CoinRepository
import com.hari.pdd.cryptotrackr.domain.repository.WatchlistRepository
import com.hari.pdd.cryptotrackr.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    private val watchlistRepository: WatchlistRepository,
    private val alertRepository: AlertRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val coinId: String = savedStateHandle.get<String>("coinId") ?: ""

    private val _uiState = MutableStateFlow(CoinDetailUiState())
    val uiState: StateFlow<CoinDetailUiState> = _uiState.asStateFlow()

    private var chartJob: Job? = null

    init {
        loadCoinDetail()
        loadChart()
        observeWatchlist()
    }

    fun onEvent(event: CoinDetailEvent) {
        when (event) {
            is CoinDetailEvent.LoadCoinDetail -> loadCoinDetail()
            is CoinDetailEvent.SelectPeriod -> selectPeriod(event.period)
            is CoinDetailEvent.RetryChart -> loadChart()
            is CoinDetailEvent.ToggleWatchlist -> toggleWatchlist()
            is CoinDetailEvent.ShowAddAlertDialog -> _uiState.update { it.copy(showAddAlertDialog = true) }
            is CoinDetailEvent.HideAddAlertDialog -> _uiState.update { it.copy(showAddAlertDialog = false) }
            is CoinDetailEvent.AddPriceAlert -> addPriceAlert(event.targetPrice, event.isAbove)
        }
    }

    private fun loadCoinDetail() {
        viewModelScope.launch {
            coinRepository.getCoinDetail(coinId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                coinDetail = resource.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = resource.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadChart(period: ChartPeriod = _uiState.value.selectedPeriod) {
        chartJob?.cancel()
        chartJob = viewModelScope.launch {
            _uiState.update { it.copy(isChartLoading = true, chartError = null) }

            coinRepository.getMarketChart(coinId, period.days).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                chartData = resource.data,
                                isChartLoading = false,
                                chartError = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isChartLoading = false,
                                chartError = resource.message ?: "Failed to load chart data"
                            )
                        }
                    }
                    is Resource.Loading -> { }
                }
            }
        }
    }

    private fun selectPeriod(period: ChartPeriod) {
        _uiState.update { it.copy(selectedPeriod = period) }
        loadChart(period)
    }

    private fun observeWatchlist() {
        viewModelScope.launch {
            watchlistRepository.isInWatchlist(coinId).collect { isInWatchlist ->
                _uiState.update { it.copy(isInWatchlist = isInWatchlist) }
            }
        }
    }

    private fun toggleWatchlist() {
        viewModelScope.launch {
            watchlistRepository.toggleWatchlist(coinId)
        }
    }

    private fun addPriceAlert(targetPrice: Double, isAbove: Boolean) {
        val coinDetail = _uiState.value.coinDetail ?: return

        viewModelScope.launch {
            val alert = PriceAlert(
                coinId = coinDetail.id,
                coinName = coinDetail.name,
                coinSymbol = coinDetail.symbol,
                coinImage = coinDetail.image,
                targetPrice = targetPrice,
                isAbove = isAbove
            )
            alertRepository.addAlert(alert)
            _uiState.update { it.copy(showAddAlertDialog = false) }
        }
    }
}
