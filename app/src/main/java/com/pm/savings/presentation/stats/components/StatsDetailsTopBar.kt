package com.pm.savings.presentation.stats.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pm.savings.presentation.core.components.TopBarButton
import com.pm.savings.R
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.ui.theme.textColor
import com.pm.savings.presentation.ui.theme.topBarHeadline

@Composable
fun StatsDetailsTopBar(
    modifier: Modifier = Modifier,
    onLeftArrowClick: () -> Unit,
    onRightArrowClick: () -> Unit,
    isPrevButtonVisible: Boolean,
    isNextButtonVisible: Boolean,
    text: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Constants.TOP_BAR_HEIGHT),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TopBarButton(
                enabled = isPrevButtonVisible,
                iconPath = R.drawable.arrow_left,
                contentDescription = "back",
                onClick = onLeftArrowClick,
                isWhite = false,
            )
            Text(
                text = text, style = topBarHeadline, color = textColor
            )
            TopBarButton(
                enabled = isNextButtonVisible,
                iconPath = R.drawable.arrow_right,
                contentDescription = "change color",
                onClick = onRightArrowClick,
                isWhite = false
            )
        }
    }
}