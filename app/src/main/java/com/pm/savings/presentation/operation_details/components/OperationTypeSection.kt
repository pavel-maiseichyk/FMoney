package com.pm.savings.presentation.operation_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.presentation.operation_details.components.OperationTypeButton

@Composable
fun OperationTypeSection(
    modifier: Modifier = Modifier,
    selectedType: OperationType,
    color: Color,
    onTypeClick: (OperationType) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            OperationTypeButton(
                isSelected = selectedType == OperationType.Income,
                color = color,
                onClick = { onTypeClick(OperationType.Income) },
                type = OperationType.Income,
                modifier = Modifier.weight(1f)
            )
            OperationTypeButton(
                isSelected = selectedType == OperationType.Spending,
                color = color,
                onClick = { onTypeClick(OperationType.Spending) },
                type = OperationType.Spending,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            OperationTypeButton(
                isSelected = selectedType == OperationType.Transfer,
                color = color,
                onClick = { onTypeClick(OperationType.Transfer) },
                type = OperationType.Transfer,
                modifier = Modifier.weight(1f)
            )
            OperationTypeButton(
                isSelected = selectedType == OperationType.Saving,
                color = color,
                onClick = { onTypeClick(OperationType.Saving) },
                type = OperationType.Saving,
                modifier = Modifier.weight(1f)
            )
        }
    }
}