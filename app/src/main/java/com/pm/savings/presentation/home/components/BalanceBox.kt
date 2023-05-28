package com.pm.savings.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pm.savings.R
import com.pm.savings.presentation.ui.theme.poppins
import com.pm.savings.presentation.ui.theme.textColor

@Composable
fun BalanceBox(
    modifier: Modifier = Modifier,
    balance: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(textColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                fontFamily = poppins,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            ),
            text = buildAnnotatedString {
                append("${stringResource(id = R.string.balance)}: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append(balance)
                }
            }
        )
    }
}

@Preview
@Composable
fun BalancePrev() {
    BalanceBox(balance = "20,000 PLN")
}