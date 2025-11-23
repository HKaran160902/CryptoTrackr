package com.hari.pdd.cryptotrackr.ui.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hari.pdd.cryptotrackr.ui.theme.ChartGreen
import com.hari.pdd.cryptotrackr.ui.theme.ChartRed
import com.hari.pdd.cryptotrackr.util.formatPercentage

@Composable
fun PriceChangeChip(
    percentage: Double,
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    val isPositive = percentage >= 0
    val backgroundColor = if (isPositive) ChartGreen.copy(alpha = 0.15f) else ChartRed.copy(alpha = 0.15f)
    val contentColor = if (isPositive) ChartGreen else ChartRed
    val icon = if (isPositive) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = if (compact) 4.dp else 8.dp, vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!compact) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = percentage.formatPercentage(),
                style = if (compact) MaterialTheme.typography.labelSmall else MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
        }
    }
}

@Composable
fun PriceChangeText(
    percentage: Double,
    modifier: Modifier = Modifier
) {
    val isPositive = percentage >= 0
    val contentColor = if (isPositive) ChartGreen else ChartRed

    Text(
        text = percentage.formatPercentage(),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        color = contentColor,
        modifier = modifier
    )
}
