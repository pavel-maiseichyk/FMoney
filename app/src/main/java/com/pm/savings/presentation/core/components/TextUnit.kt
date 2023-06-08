package com.pm.savings.presentation.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pm.savings.presentation.ui.theme.small16

@Composable
fun TextUnit(
    smallText: String,
    bigText: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isDigits: Boolean
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = smallText,
            style = small16
        )
        EditableText(
            text = bigText,
            hint = hint,
            onValueChange = onValueChange,
            isDigits = isDigits
        )
    }
}