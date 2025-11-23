package com.hari.pdd.cryptotrackr.ui.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hari.pdd.cryptotrackr.domain.model.PricePoint
import com.hari.pdd.cryptotrackr.ui.theme.ChartGreen
import com.hari.pdd.cryptotrackr.ui.theme.ChartRed
import com.hari.pdd.cryptotrackr.ui.theme.Surface
import com.hari.pdd.cryptotrackr.ui.theme.TextPrimary
import com.hari.pdd.cryptotrackr.ui.theme.TextSecondary
import com.hari.pdd.cryptotrackr.util.formatAxisPrice
import com.hari.pdd.cryptotrackr.util.formatChartTime
import com.hari.pdd.cryptotrackr.util.formatPrice
import kotlin.math.roundToInt

@Composable
fun PriceLineChart(
    pricePoints: List<PricePoint>,
    modifier: Modifier = Modifier,
    isPositive: Boolean = true,
    showGradient: Boolean = true,
    periodDays: String = "1",
    enableTooltip: Boolean = true
) {
    if (pricePoints.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No chart data available",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
        }
        return
    }

    val lineColor = if (isPositive) ChartGreen else ChartRed
    val gradientStart = lineColor.copy(alpha = 0.3f)
    val gradientEnd = lineColor.copy(alpha = 0.0f)

    val prices = remember(pricePoints) { pricePoints.map { it.price } }
    val minPrice = remember(prices) { prices.minOrNull() ?: 0.0 }
    val maxPrice = remember(prices) { prices.maxOrNull() ?: 0.0 }
    val priceRange = remember(minPrice, maxPrice) {
        if (maxPrice - minPrice == 0.0) 1.0 else maxPrice - minPrice
    }

    // Generate Y-axis labels (5 labels)
    val yAxisLabels = remember(minPrice, maxPrice) {
        val step = (maxPrice - minPrice) / 4
        (0..4).map { i -> minPrice + (step * i) }
    }

    // Generate X-axis labels (5 labels)
    val xAxisLabels = remember(pricePoints, periodDays) {
        if (pricePoints.size < 5) {
            pricePoints.map { it.timestamp.formatChartTime(periodDays) }
        } else {
            val step = (pricePoints.size - 1) / 4
            (0..4).map { i ->
                val index = (i * step).coerceAtMost(pricePoints.size - 1)
                pricePoints[index].timestamp.formatChartTime(periodDays)
            }
        }
    }

    // Tooltip state
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var touchX by remember { mutableStateOf(0f) }

    val density = LocalDensity.current
    val yAxisWidth = 50.dp
    val xAxisHeight = 24.dp
    val chartPadding = 8.dp

    Column(modifier = modifier.fillMaxSize()) {
        // Main chart area with Y-axis
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // Y-axis labels
            Column(
                modifier = Modifier
                    .width(yAxisWidth)
                    .fillMaxHeight()
                    .padding(end = 4.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
            ) {
                yAxisLabels.reversed().forEach { price ->
                    Text(
                        text = price.formatAxisPrice(),
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary,
                        fontSize = 10.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Chart canvas
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(enableTooltip, pricePoints) {
                            if (enableTooltip) {
                                detectTapGestures { offset ->
                                    val chartWidth = size.width.toFloat()
                                    val stepX = chartWidth / (prices.size - 1).coerceAtLeast(1)
                                    val index = (offset.x / stepX).roundToInt()
                                        .coerceIn(0, prices.size - 1)

                                    if (selectedIndex == index) {
                                        selectedIndex = null
                                    } else {
                                        selectedIndex = index
                                        touchX = offset.x
                                    }
                                }
                            }
                        }
                ) {
                    val width = size.width
                    val height = size.height
                    val stepX = width / (prices.size - 1).coerceAtLeast(1)

                    // Create the line path
                    val linePath = Path()
                    val fillPath = Path()

                    prices.forEachIndexed { index, price ->
                        val x = index * stepX
                        val normalizedPrice = ((price - minPrice) / priceRange).toFloat()
                        val y = height - (normalizedPrice * height * 0.9f) - (height * 0.05f)

                        if (index == 0) {
                            linePath.moveTo(x, y)
                            fillPath.moveTo(x, height)
                            fillPath.lineTo(x, y)
                        } else {
                            linePath.lineTo(x, y)
                            fillPath.lineTo(x, y)
                        }
                    }

                    // Complete fill path
                    fillPath.lineTo(width, height)
                    fillPath.close()

                    // Draw gradient fill
                    if (showGradient) {
                        drawPath(
                            path = fillPath,
                            brush = Brush.verticalGradient(
                                colors = listOf(gradientStart, gradientEnd),
                                startY = 0f,
                                endY = height
                            )
                        )
                    }

                    // Draw line
                    drawPath(
                        path = linePath,
                        color = lineColor,
                        style = Stroke(
                            width = 2.dp.toPx(),
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )

                    // Draw tooltip line and dot
                    selectedIndex?.let { index ->
                        val x = index * stepX
                        val price = prices[index]
                        val normalizedPrice = ((price - minPrice) / priceRange).toFloat()
                        val y = height - (normalizedPrice * height * 0.9f) - (height * 0.05f)

                        // Dotted vertical line
                        drawLine(
                            color = TextSecondary,
                            start = Offset(x, 0f),
                            end = Offset(x, height),
                            strokeWidth = 1.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                        )

                        // Dot at the price point
                        drawCircle(
                            color = lineColor,
                            radius = 6.dp.toPx(),
                            center = Offset(x, y)
                        )
                        drawCircle(
                            color = Color.White,
                            radius = 3.dp.toPx(),
                            center = Offset(x, y)
                        )
                    }
                }

                // Tooltip bubble
                selectedIndex?.let { index ->
                    val pricePoint = pricePoints[index]
                    val chartWidth = with(density) { (modifier as? Modifier)?.let { 200.dp.toPx() } ?: 200f }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset { IntOffset(0, 0) }
                            .background(
                                color = Surface,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = pricePoint.price.formatPrice(),
                                style = MaterialTheme.typography.labelMedium,
                                color = TextPrimary,
                                fontSize = 12.sp
                            )
                            Text(
                                text = pricePoint.timestamp.formatChartTime(periodDays),
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // X-axis labels
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(xAxisHeight)
                .padding(start = yAxisWidth),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            xAxisLabels.forEach { label ->
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
fun SparklineChart(
    prices: List<Double>,
    modifier: Modifier = Modifier,
    isPositive: Boolean = true
) {
    if (prices.isEmpty()) return

    val lineColor = if (isPositive) ChartGreen else ChartRed
    val minPrice = remember(prices) { prices.minOrNull() ?: 0.0 }
    val maxPrice = remember(prices) { prices.maxOrNull() ?: 0.0 }
    val priceRange = remember(minPrice, maxPrice) {
        if (maxPrice - minPrice == 0.0) 1.0 else maxPrice - minPrice
    }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        val width = size.width
        val height = size.height
        val stepX = width / (prices.size - 1).coerceAtLeast(1)

        val path = Path()

        prices.forEachIndexed { index, price ->
            val x = index * stepX
            val normalizedPrice = ((price - minPrice) / priceRange).toFloat()
            val y = height - (normalizedPrice * height * 0.8f) - (height * 0.1f)

            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }

        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(
                width = 1.5.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}
