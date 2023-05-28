package com.pm.savings.presentation.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.presentation.ui.theme.small16
import com.pm.savings.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTypeButtons(
    modifier: Modifier = Modifier,
    selectedType: OperationType,
    onTypeClick: (OperationType) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(
            modifier = Modifier.weight(1f),
            onClick = { onTypeClick(OperationType.Spending) },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(
                width = 1.dp,
                color = if (selectedType == OperationType.Spending) textColor else Color.Transparent
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "spendings",
                    style = small16,
                    color = textColor
                )
            }
        }
        Card(
            modifier = Modifier.weight(1f),
            onClick = { onTypeClick(OperationType.Income) },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(
                width = 1.dp,
                color = if (selectedType == OperationType.Income) textColor else Color.Transparent
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "income",
                    style = small16,
                    color = textColor
                )
            }
        }
    }
}