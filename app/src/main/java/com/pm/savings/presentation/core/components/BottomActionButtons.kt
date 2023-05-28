package com.pm.finance_kmm.android.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pm.savings.presentation.core.components.DeleteButton
import com.pm.savings.presentation.core.components.SaveButton

@Composable
fun BottomActionButtons(
    modifier: Modifier = Modifier,
    shouldShowDelete: Boolean,
    textColor: Color,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (shouldShowDelete) {
            DeleteButton(
                onClick = onDeleteClick,
                modifier = Modifier.weight(1f)
            )
        }
        SaveButton(
            onClick = onSaveClick,
            modifier = Modifier.weight(1f),
            textColor = textColor
        )
    }
}