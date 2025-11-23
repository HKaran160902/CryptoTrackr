package com.hari.pdd.cryptotrackr.ui.screens.alerts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hari.pdd.cryptotrackr.domain.model.PriceAlert
import com.hari.pdd.cryptotrackr.domain.repository.AlertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertsViewModel @Inject constructor(
    private val alertRepository: AlertRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlertsUiState())
    val uiState: StateFlow<AlertsUiState> = _uiState.asStateFlow()

    init {
        loadAlerts()
    }

    fun onEvent(event: AlertsEvent) {
        when (event) {
            is AlertsEvent.LoadAlerts -> loadAlerts()
            is AlertsEvent.ToggleAlert -> toggleAlert(event.alertId, event.isEnabled)
            is AlertsEvent.ShowDeleteDialog -> _uiState.update {
                it.copy(showDeleteDialog = true, alertToDelete = event.alert)
            }
            is AlertsEvent.HideDeleteDialog -> _uiState.update {
                it.copy(showDeleteDialog = false, alertToDelete = null)
            }
            is AlertsEvent.ConfirmDelete -> deleteAlert()
        }
    }

    private fun loadAlerts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            alertRepository.getAllAlerts().collect { alerts ->
                _uiState.update {
                    it.copy(
                        alerts = alerts,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun toggleAlert(alertId: Long, isEnabled: Boolean) {
        viewModelScope.launch {
            alertRepository.setAlertEnabled(alertId, isEnabled)
        }
    }

    private fun deleteAlert() {
        val alert = _uiState.value.alertToDelete ?: return

        viewModelScope.launch {
            alertRepository.deleteAlert(alert)
            _uiState.update {
                it.copy(showDeleteDialog = false, alertToDelete = null)
            }
        }
    }
}
