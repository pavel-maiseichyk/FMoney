package com.pm.savings.presentation.stats.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.pm.savings.presentation.stats.CategoryWithOperationsUi
import com.pm.savings.presentation.ui.theme.small16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    categoryWithOperationsUi: CategoryWithOperationsUi,
    currency: String,
    onClick: (String) -> Unit,
) {
    val lineWidth = remember { Animatable(0f) }
    val maxRadius = LocalConfiguration.current.screenWidthDp.toFloat() * 4

    val category = categoryWithOperationsUi.category
    val operations = categoryWithOperationsUi.operations
    val percentage = categoryWithOperationsUi.percentage

    LaunchedEffect(percentage) {
        lineWidth.animateTo(
            targetValue = percentage,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = 200f
            )
        )
    }

    var globalSize by remember { mutableStateOf(IntSize.Zero) }
    val selectionRadius = animateFloatAsState(
        targetValue = if (categoryWithOperationsUi.isExpanded) maxRadius else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = 200f
        )
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .onGloballyPositioned { coordinates ->
                globalSize = coordinates.size
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = { onClick(category.title) }
    ) {
//        Canvas(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            drawCircle(
//                color = Color(category.color).copy(alpha = 0.5f),
//                radius = selectionRadius.value,
//                center = Offset(
//                    x = 32.dp.toPx(),
//                    y = globalSize.height / 2f
//                )
//            )
//        }
        AnimatedVisibility(visible = !categoryWithOperationsUi.isExpanded) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 16.dp
                    ),
                verticalAlignment = CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(category.color).copy(alpha = 0.5f))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Row {
                        Text(
                            text = category.title,
                            style = small16
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${categoryWithOperationsUi.sum} $currency",
                            style = small16
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Canvas(
                        modifier = Modifier
                            .width(globalSize.width.dp)
                            .height(6.dp)
                    ) {
                        drawRoundRect(
                            color = Color(category.color).copy(alpha = 0.5f),
                            size = size,
                            cornerRadius = CornerRadius(100f)
                        )
                        drawRoundRect(
                            color = Color(category.color),
                            size = Size(
                                width = lineWidth.value * size.width / 100,
                                height = size.height
                            ),
                            cornerRadius = CornerRadius(100f)
                        )
                    }
                }
            }
        }
        AnimatedVisibility(categoryWithOperationsUi.isExpanded) {
            Column {
                operations.forEach { operation ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .padding(vertical = 4.dp),
                        horizontalArrangement = SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Text(
                            text = operation.title,
                            style = small16
                        )
                        Text(
                            text = "${operation.sum} ${operation.currency}",
                            style = small16
                        )
                    }
                }
            }
        }
    }
}