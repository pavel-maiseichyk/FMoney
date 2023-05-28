package com.pm.savings.presentation.core.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.pm.savings.presentation.ui.theme.textColor

@Composable
fun TopBarButton(
    @DrawableRes iconPath: Int,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isWhite: Boolean,
    enabled: Boolean = true
) {
    IconButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = if (isWhite) Color.White else textColor,
            disabledContentColor = textColor.copy(0.1f)
        )
    ) {
        Icon(
            painter = painterResource(iconPath),
            contentDescription = contentDescription
//            modifier = modifier.clip(RoundedCornerShape(12.dp))
        )
    }
}