package com.pm.savings.presentation.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pm.savings.presentation.ui.theme.button
import com.pm.savings.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoiceButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.5f)
        )
    ) {
        Text(
            text = text,
            style = button,
            color = textColor,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}