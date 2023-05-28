package com.pm.savings.presentation.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pm.savings.R
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.ui.theme.topBarHeadline

@Composable
fun DetailsTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onColorChange: () -> Unit = {},
    isPaletteVisible: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Constants.TOP_BAR_HEIGHT),
        contentAlignment = Alignment.Center
    ) {
        TopBarButton(
            modifier = Modifier.align(Alignment.CenterStart),
            iconPath = R.drawable.arrow_left,
            contentDescription = "back",
            onClick = onBackClick,
            isWhite = true
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            style = topBarHeadline,
            color = Color.White
        )
        if (isPaletteVisible) {
            TopBarButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                iconPath = R.drawable.palette,
                contentDescription = "change color",
                onClick = onColorChange,
                isWhite = true
            )
        }
    }
}