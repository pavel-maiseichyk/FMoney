package com.pm.savings.presentation.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pm.savings.presentation.ui.theme.button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveButton(
    modifier: Modifier = Modifier,
    text: String = "save",
    onClick: () -> Unit,
    textColor: Color,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            text = text,
            style = button,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}