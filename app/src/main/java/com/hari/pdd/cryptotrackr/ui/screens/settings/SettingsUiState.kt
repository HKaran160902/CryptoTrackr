package com.hari.pdd.cryptotrackr.ui.screens.settings

data class SettingsUiState(
    val isDarkMode: Boolean = true,
    val currency: String = "usd",
    val notificationsEnabled: Boolean = true,
    val showCurrencyDialog: Boolean = false,
    val showClearCacheDialog: Boolean = false
)

sealed class SettingsEvent {
    data class SetDarkMode(val isDarkMode: Boolean) : SettingsEvent()
    data class SetCurrency(val currency: String) : SettingsEvent()
    data class SetNotificationsEnabled(val enabled: Boolean) : SettingsEvent()
    data object ShowCurrencyDialog : SettingsEvent()
    data object HideCurrencyDialog : SettingsEvent()
    data object ShowClearCacheDialog : SettingsEvent()
    data object HideClearCacheDialog : SettingsEvent()
    data object ClearCache : SettingsEvent()
}
