package com.pm.savings.presentation.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.ui.theme.topBarHeadline

@Composable
fun ProfileTopBar(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Constants.TOP_BAR_HEIGHT)
    ) {
        Text(
            text = "Profile",
            style = topBarHeadline,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}