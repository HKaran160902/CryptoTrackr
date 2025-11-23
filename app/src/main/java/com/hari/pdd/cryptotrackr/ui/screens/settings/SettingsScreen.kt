package com.hari.pdd.cryptotrackr.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.hari.pdd.cryptotrackr.ui.components.dialogs.CryptoAlertDialog
import com.hari.pdd.cryptotrackr.ui.components.topbar.CryptoTopBar
import com.hari.pdd.cryptotrackr.ui.theme.Secondary
import com.hari.pdd.cryptotrackr.util.Constants

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CryptoTopBar(
            title = "Settings",
            showBackButton = true,
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Appearance Section
            SectionHeader(title = "Appearance")

            SettingsItemSwitch(
                icon = Icons.Default.DarkMode,
                title = "Dark Mode",
                description = "Use dark theme",
                checked = uiState.isDarkMode,
                onCheckedChange = { viewModel.onEvent(SettingsEvent.SetDarkMode(it)) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Preferences Section
            SectionHeader(title = "Preferences")

            SettingsItemNavigation(
                icon = Icons.Default.MonetizationOn,
                title = "Currency",
                description = getCurrencyDisplayName(uiState.currency),
                onClick = { viewModel.onEvent(SettingsEvent.ShowCurrencyDialog) }
            )

            SettingsItemSwitch(
                icon = Icons.Default.Notifications,
                title = "Notifications",
                description = "Enable price alerts",
                checked = uiState.notificationsEnabled,
                onCheckedChange = { viewModel.onEvent(SettingsEvent.SetNotificationsEnabled(it)) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Data Section
            SectionHeader(title = "Data")

            SettingsItemNavigation(
                icon = Icons.Default.Delete,
                title = "Clear Cache",
                description = "Clear cached cryptocurrency data",
                onClick = { viewModel.onEvent(SettingsEvent.ShowClearCacheDialog) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // About Section
            SectionHeader(title = "About")

            SettingsItemInfo(
                icon = Icons.Default.Info,
                title = "Version",
                description = "1.0.0"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Footer
            Text(
                text = "CryptoTrackr",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Secondary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Track. Watch. Profit.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

    // Currency Selection Dialog
    if (uiState.showCurrencyDialog) {
        CurrencySelectionDialog(
            selectedCurrency = uiState.currency,
            onCurrencySelected = { viewModel.onEvent(SettingsEvent.SetCurrency(it)) },
            onDismiss = { viewModel.onEvent(SettingsEvent.HideCurrencyDialog) }
        )
    }

    // Clear Cache Confirmation Dialog
    if (uiState.showClearCacheDialog) {
        CryptoAlertDialog(
            title = "Clear Cache",
            message = "This will clear all cached cryptocurrency data. The data will be fetched again from the server.",
            confirmText = "Clear",
            dismissText = "Cancel",
            onConfirm = { viewModel.onEvent(SettingsEvent.ClearCache) },
            onDismiss = { viewModel.onEvent(SettingsEvent.HideClearCacheDialog) }
        )
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = Secondary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
private fun SettingsItemSwitch(
    icon: ImageVector,
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Secondary
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Secondary,
                checkedTrackColor = Secondary.copy(alpha = 0.5f),
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun SettingsItemNavigation(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Secondary
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun SettingsItemInfo(
    icon: ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Secondary
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun CurrencySelectionDialog(
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Select Currency",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(16.dp))

                Constants.SUPPORTED_CURRENCIES.forEach { (code, name) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onCurrencySelected(code) }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedCurrency == code,
                            onClick = { onCurrencySelected(code) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Secondary,
                                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

private fun getCurrencyDisplayName(code: String): String {
    return Constants.SUPPORTED_CURRENCIES.find { it.first == code }?.second ?: code.uppercase()
}
