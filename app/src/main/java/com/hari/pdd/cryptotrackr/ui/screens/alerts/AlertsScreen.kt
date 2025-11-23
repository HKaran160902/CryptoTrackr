package com.hari.pdd.cryptotrackr.ui.screens.alerts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hari.pdd.cryptotrackr.domain.model.PriceAlert
import com.hari.pdd.cryptotrackr.ui.components.dialogs.DeleteConfirmationDialog
import com.hari.pdd.cryptotrackr.ui.components.states.EmptyAlertsState
import com.hari.pdd.cryptotrackr.ui.components.states.LoadingIndicator
import com.hari.pdd.cryptotrackr.ui.components.topbar.CryptoTopBar
import com.hari.pdd.cryptotrackr.ui.theme.Background
import com.hari.pdd.cryptotrackr.ui.theme.ChartGreen
import com.hari.pdd.cryptotrackr.ui.theme.ChartRed
import com.hari.pdd.cryptotrackr.ui.theme.Error
import com.hari.pdd.cryptotrackr.ui.theme.Primary
import com.hari.pdd.cryptotrackr.ui.theme.Secondary
import com.hari.pdd.cryptotrackr.ui.theme.Surface
import com.hari.pdd.cryptotrackr.ui.theme.SurfaceVariant
import com.hari.pdd.cryptotrackr.ui.theme.TextPrimary
import com.hari.pdd.cryptotrackr.ui.theme.TextSecondary
import com.hari.pdd.cryptotrackr.util.formatPrice

@Composable
fun AlertsScreen(
    onCoinClick: (String) -> Unit,
    viewModel: AlertsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        CryptoTopBar(title = "Price Alerts")

        when {
            uiState.isLoading -> {
                LoadingIndicator()
            }

            uiState.alerts.isEmpty() -> {
                EmptyAlertsState()
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = uiState.alerts,
                        key = { it.id }
                    ) { alert ->
                        AlertItem(
                            alert = alert,
                            onClick = { onCoinClick(alert.coinId) },
                            onToggle = { isEnabled ->
                                viewModel.onEvent(AlertsEvent.ToggleAlert(alert.id, isEnabled))
                            },
                            onDelete = {
                                viewModel.onEvent(AlertsEvent.ShowDeleteDialog(alert))
                            }
                        )
                        HorizontalDivider(
                            color = SurfaceVariant,
                            thickness = 0.5.dp
                        )
                    }
                }
            }
        }
    }

    // Delete confirmation dialog
    if (uiState.showDeleteDialog && uiState.alertToDelete != null) {
        DeleteConfirmationDialog(
            itemName = "alert",
            onConfirm = { viewModel.onEvent(AlertsEvent.ConfirmDelete) },
            onDismiss = { viewModel.onEvent(AlertsEvent.HideDeleteDialog) }
        )
    }
}

@Composable
private fun AlertItem(
    alert: PriceAlert,
    onClick: () -> Unit,
    onToggle: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(Surface)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = alert.coinImage,
            contentDescription = alert.coinName,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = alert.coinName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (alert.isAbove) Icons.Default.TrendingUp else Icons.Default.TrendingDown,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = if (alert.isAbove) ChartGreen else ChartRed
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${alert.conditionText} ${alert.targetPrice.formatPrice()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }

            if (alert.isTriggered) {
                Text(
                    text = "Triggered",
                    style = MaterialTheme.typography.labelSmall,
                    color = Secondary
                )
            }
        }

        Switch(
            checked = alert.isEnabled,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Secondary,
                checkedTrackColor = Secondary.copy(alpha = 0.5f),
                uncheckedThumbColor = TextSecondary,
                uncheckedTrackColor = SurfaceVariant
            )
        )

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Error
            )
        }
    }
}
