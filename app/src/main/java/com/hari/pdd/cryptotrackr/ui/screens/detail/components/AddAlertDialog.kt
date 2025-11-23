package com.hari.pdd.cryptotrackr.ui.screens.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hari.pdd.cryptotrackr.ui.components.buttons.CryptoOutlinedButton
import com.hari.pdd.cryptotrackr.ui.components.buttons.CryptoPrimaryButton
import com.hari.pdd.cryptotrackr.ui.components.inputs.CryptoTextField
import com.hari.pdd.cryptotrackr.ui.theme.Primary
import com.hari.pdd.cryptotrackr.ui.theme.Secondary
import com.hari.pdd.cryptotrackr.ui.theme.TextPrimary
import com.hari.pdd.cryptotrackr.ui.theme.TextSecondary
import com.hari.pdd.cryptotrackr.util.formatPrice

@Composable
fun AddAlertDialog(
    coinName: String,
    currentPrice: Double,
    onDismiss: () -> Unit,
    onConfirm: (targetPrice: Double, isAbove: Boolean) -> Unit
) {
    var targetPriceText by remember { mutableStateOf("") }
    var isAbove by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = com.hari.pdd.cryptotrackr.ui.theme.Surface
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Set Price Alert",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Alert me when $coinName price...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Above/Below selector
                Row {
                    FilterChip(
                        selected = isAbove,
                        onClick = { isAbove = true },
                        label = { Text("Goes Above") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Secondary,
                            selectedLabelColor = Primary,
                            containerColor = com.hari.pdd.cryptotrackr.ui.theme.SurfaceVariant,
                            labelColor = TextSecondary
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    FilterChip(
                        selected = !isAbove,
                        onClick = { isAbove = false },
                        label = { Text("Goes Below") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Secondary,
                            selectedLabelColor = Primary,
                            containerColor = com.hari.pdd.cryptotrackr.ui.theme.SurfaceVariant,
                            labelColor = TextSecondary
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                CryptoTextField(
                    value = targetPriceText,
                    onValueChange = {
                        targetPriceText = it
                        hasError = false
                    },
                    label = "Target Price",
                    placeholder = "Enter target price",
                    keyboardType = KeyboardType.Decimal,
                    isError = hasError,
                    errorMessage = "Please enter a valid price"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Current price: ${currentPrice.formatPrice()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    CryptoOutlinedButton(
                        text = "Cancel",
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    CryptoPrimaryButton(
                        text = "Set Alert",
                        onClick = {
                            val price = targetPriceText.toDoubleOrNull()
                            if (price != null && price > 0) {
                                onConfirm(price, isAbove)
                            } else {
                                hasError = true
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
