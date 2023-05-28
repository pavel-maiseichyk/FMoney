package com.pm.savings.presentation.savings.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pm.savings.R
import com.pm.savings.domain.savings.model.Saving
import com.pm.savings.presentation.ui.theme.headlineSmall
import com.pm.savings.presentation.ui.theme.poppins
import com.pm.savings.presentation.ui.theme.small12
import com.pm.savings.presentation.ui.theme.textColor
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavingCard(
    modifier: Modifier = Modifier,
    saving: Saving,
    onClick: () -> Unit
) {

    val percentage = if (saving.endSum != 0.0) (saving.savedSum * 100.0 / saving.endSum).roundToInt() else 0

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(saving.color)
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 16.dp
            )
        ) {
            Text(
                text = saving.title,
                style = headlineSmall.copy(color = Color.White)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.balance),
                    style = small12.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$percentage%",
                    style = small12.copy(color = Color.White)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Canvas(modifier = Modifier.fillMaxWidth()) {
                drawLine(
                    Color.White.copy(alpha = 0.75f),
                    start = Offset.Zero,
                    end = Offset(x = size.width, y = center.y),
                    strokeWidth = 16.dp.value,
                    cap = StrokeCap.Round
                )
                drawLine(
                    textColor.copy(alpha = 0.9f),
                    start = Offset.Zero,
                    end = Offset(x = size.width * percentage / 100, y = center.y),
                    strokeWidth = 16.dp.value,
                    cap = StrokeCap.Round
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = SemiBold,
                            fontFamily = poppins
                        )
                    ) {
                        append("${saving.savedSum} ${saving.currency} ")
                    }

                    withStyle(
                        SpanStyle(
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = Normal,
                            fontFamily = poppins
                        )
                    ) {
                        append("${stringResource(id = R.string.of)} ${saving.endSum} ${saving.currency}")
                    }
                }
            )
        }
    }
}