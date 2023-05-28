package com.pm.savings.presentation.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pm.savings.presentation.ui.theme.small16
import com.pm.savings.presentation.ui.theme.topBarHeadline

@Composable
fun SubHeadlineWithAction(
    modifier: Modifier = Modifier,
    text: String,
    secondText: String,
    onTextClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = text, style = topBarHeadline)
        TextButton(onClick = onTextClick, shape = CircleShape) {
            Text(text = secondText, style = small16, color = Color(0xFF489FCD))
        }
    }
}