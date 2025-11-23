package com.hari.pdd.cryptotrackr.ui.screens.detail

import com.hari.pdd.cryptotrackr.domain.model.ChartData
import com.hari.pdd.cryptotrackr.domain.model.ChartPeriod
import com.hari.pdd.cryptotrackr.domain.model.CoinDetail

data class CoinDetailUiState(
    val coinDetail: CoinDetail? = null,
    val chartData: ChartData? = null,
    val isLoading: Boolean = false,
    val isChartLoading: Boolean = false,
    val error: String? = null,
    val chartError: String? = null,
    val selectedPeriod: ChartPeriod = ChartPeriod.DAY_1,
    val isInWatchlist: Boolean = false,
    val showAddAlertDialog: Boolean = false
)

sealed class CoinDetailEvent {
    data object LoadCoinDetail : CoinDetailEvent()
    data class SelectPeriod(val period: ChartPeriod) : CoinDetailEvent()
    data object RetryChart : CoinDetailEvent()
    data object ToggleWatchlist : CoinDetailEvent()
    data object ShowAddAlertDialog : CoinDetailEvent()
    data object HideAddAlertDialog : CoinDetailEvent()
    data class AddPriceAlert(val targetPrice: Double, val isAbove: Boolean) : CoinDetailEvent()
}
