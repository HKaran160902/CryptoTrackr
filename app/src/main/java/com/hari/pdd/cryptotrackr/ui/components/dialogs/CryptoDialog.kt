package com.hari.pdd.cryptotrackr.ui.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hari.pdd.cryptotrackr.ui.theme.Primary
import com.hari.pdd.cryptotrackr.ui.theme.Secondary
import com.hari.pdd.cryptotrackr.ui.theme.Surface
import com.hari.pdd.cryptotrackr.ui.theme.TextPrimary
import com.hari.pdd.cryptotrackr.ui.theme.TextSecondary

@Composable
fun CryptoAlertDialog(
    title: String,
    message: String,
    confirmText: String = "Confirm",
    dismissText: String = "Cancel",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    isDangerous: Boolean = false
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = confirmText,
                    color = if (isDangerous) com.hari.pdd.cryptotrackr.ui.theme.Error else Secondary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = dismissText,
                    color = TextSecondary
                )
            }
        },
        containerColor = Surface,
        titleContentColor = TextPrimary,
        textContentColor = TextSecondary
    )
}

@Composable
fun CryptoBottomSheetDialog(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Surface
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(
    itemName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    CryptoAlertDialog(
        title = "Delete $itemName?",
        message = "This action cannot be undone. Are you sure you want to delete this $itemName?",
        confirmText = "Delete",
        dismissText = "Cancel",
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        isDangerous = true
    )
}
