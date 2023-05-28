package com.pm.savings.presentation.operation_details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.presentation.ui.theme.button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperationTypeButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    color: Color,
    onClick: () -> Unit,
    type: OperationType
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.White else Color.White.copy(alpha = 0.5f),
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            text = type.title,
            style = button,
            color = if (isSelected) color else Color.White,
            textAlign = TextAlign.Center
        )
    }
}