package com.pm.finance.presentation.stats.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import com.pm.savings.presentation.ui.theme.headline
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.stats.CategoryWithOperationsUi
import com.pm.savings.presentation.ui.theme.topBarHeadline
import java.util.*

@OptIn(ExperimentalTextApi::class)
@Composable
fun DoughnutChart(
    modifier: Modifier = Modifier,
    categoryWithOperationsUis: List<CategoryWithOperationsUi>
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val size = remember { screenWidth - Constants.HORIZONTAL_PADDING * 8 }

    val sumOfValues = categoryWithOperationsUis.sumOf { it.sum }
    val colors = categoryWithOperationsUis.map { Color(it.category.color) }

    val proportions = categoryWithOperationsUis.map { it.percentage }

    val sweepAngles = proportions.map {
        360 * it / 100
    }

    val textMeasurer = rememberTextMeasurer()
    val primaryTextSize = remember {
        textMeasurer.measure(
            text = AnnotatedString("$sumOfValues"),
            style = headline
        ).size
    }
    val secondaryTextSize = remember {
        textMeasurer.measure(
            text = AnnotatedString("PLN"),
            style = topBarHeadline
        ).size
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(
            modifier = Modifier.size(size = size)
        ) {
            var startAngle = -90f
            if (sumOfValues == 0.0) {
                drawArc(
                    color = Color.LightGray,
                    startAngle = startAngle,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Butt)
                )
            }
            categoryWithOperationsUis.forEachIndexed { index, value ->
                drawArc(
                    color = colors[index],
                    startAngle = startAngle,
                    sweepAngle = sweepAngles[index],
                    useCenter = false,
                    style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Butt)
                )
                startAngle += sweepAngles[index]
            }
            drawText(
                textMeasurer = textMeasurer,
                text = "$sumOfValues",
                style = headline,
                topLeft = Offset(
                    x = (this.size.width - primaryTextSize.width) / 2f,
                    y = (this.size.height - primaryTextSize.height - 24.dp.toPx()) / 2f
                )
            )
            drawText(
                textMeasurer = textMeasurer,
                text = "PLN",
                style = topBarHeadline,
                topLeft = Offset(
                    x = (this.size.width - secondaryTextSize.width) / 2f,
                    y = (this.size.height - secondaryTextSize.height + primaryTextSize.height + 4.dp.toPx()) / 2f
                )
            )
        }
    }
}
