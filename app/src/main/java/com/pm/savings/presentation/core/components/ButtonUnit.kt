package com.pm.savings.presentation.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pm.savings.presentation.ui.theme.small16

@Composable
fun ButtonUnit(
    smallText: String,
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = smallText,
            style = small16
        )
        ChoiceButton(
            text = buttonText,
            onClick = onClick
        )
    }
}