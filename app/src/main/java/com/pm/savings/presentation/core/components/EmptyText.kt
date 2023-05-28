package com.pm.savings.presentation.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.pm.savings.presentation.ui.theme.headlineSmall
import com.pm.savings.presentation.ui.theme.textColor

@Composable
fun EmptyText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = headlineSmall,
            color = textColor.copy(alpha = 0.5f),
            fontSize = fontSize,
            textAlign = TextAlign.Center
        )
    }
}