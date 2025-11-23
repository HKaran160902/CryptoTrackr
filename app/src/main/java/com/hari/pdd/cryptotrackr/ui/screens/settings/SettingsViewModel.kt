package com.hari.pdd.cryptotrackr.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hari.pdd.cryptotrackr.data.local.preferences.UserPreferences
import com.hari.pdd.cryptotrackr.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        loadSettings()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SetDarkMode -> setDarkMode(event.isDarkMode)
            is SettingsEvent.SetCurrency -> setCurrency(event.currency)
            is SettingsEvent.SetNotificationsEnabled -> setNotificationsEnabled(event.enabled)
            is SettingsEvent.ShowCurrencyDialog -> _uiState.update { it.copy(showCurrencyDialog = true) }
            is SettingsEvent.HideCurrencyDialog -> _uiState.update { it.copy(showCurrencyDialog = false) }
            is SettingsEvent.ShowClearCacheDialog -> _uiState.update { it.copy(showClearCacheDialog = true) }
            is SettingsEvent.HideClearCacheDialog -> _uiState.update { it.copy(showClearCacheDialog = false) }
            is SettingsEvent.ClearCache -> clearCache()
        }
    }

    private fun loadSettings() {
        viewModelScope.launch {
            combine(
                userPreferences.isDarkMode,
                userPreferences.currency,
                userPreferences.notificationsEnabled
            ) { isDarkMode, currency, notificationsEnabled ->
                Triple(isDarkMode, currency, notificationsEnabled)
            }.collect { (isDarkMode, currency, notificationsEnabled) ->
                _uiState.update {
                    it.copy(
                        isDarkMode = isDarkMode,
                        currency = currency,
                        notificationsEnabled = notificationsEnabled
                    )
                }
            }
        }
    }

    private fun setDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            userPreferences.setDarkMode(isDarkMode)
        }
    }

    private fun setCurrency(currency: String) {
        viewModelScope.launch {
            userPreferences.setCurrency(currency)
            // Clear cache so data is refreshed with new currency
            coinRepository.clearCache()
            _uiState.update { it.copy(showCurrencyDialog = false) }
        }
    }

    private fun setNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            userPreferences.setNotificationsEnabled(enabled)
        }
    }

    private fun clearCache() {
        viewModelScope.launch {
            coinRepository.clearCache()
            _uiState.update { it.copy(showClearCacheDialog = false) }
        }
    }
}
