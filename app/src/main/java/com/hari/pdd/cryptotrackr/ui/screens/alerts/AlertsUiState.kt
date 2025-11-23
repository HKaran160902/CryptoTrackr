package com.hari.pdd.cryptotrackr.ui.screens.alerts

import com.hari.pdd.cryptotrackr.domain.model.PriceAlert

data class AlertsUiState(
    val alerts: List<PriceAlert> = emptyList(),
    val isLoading: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val alertToDelete: PriceAlert? = null
)

sealed class AlertsEvent {
    data object LoadAlerts : AlertsEvent()
    data class ToggleAlert(val alertId: Long, val isEnabled: Boolean) : AlertsEvent()
    data class ShowDeleteDialog(val alert: PriceAlert) : AlertsEvent()
    data object HideDeleteDialog : AlertsEvent()
    data object ConfirmDelete : AlertsEvent()
}
