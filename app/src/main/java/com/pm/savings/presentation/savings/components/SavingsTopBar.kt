package com.pm.savings.presentation.savings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pm.savings.presentation.core.components.TopBarButton
import com.pm.savings.R
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.ui.theme.topBarHeadline

@Composable
fun SavingsTopBar(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Constants.TOP_BAR_HEIGHT)
    ) {
        Text(
            text = stringResource(R.string.savings_title),
            style = topBarHeadline,
            modifier = Modifier.align(Alignment.Center)
        )
        TopBarButton(
            iconPath = R.drawable.add,
            contentDescription = stringResource(R.string.add),
            onClick = onAddClick,
            modifier = Modifier.align(Alignment.CenterEnd),
            isWhite = false
        )
    }
}