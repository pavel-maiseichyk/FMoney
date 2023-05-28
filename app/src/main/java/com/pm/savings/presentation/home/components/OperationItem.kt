package com.pm.savings.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pm.finance_kmm.android.*
import com.pm.savings.domain.operations.model.Operation
import com.pm.savings.presentation.core.AppColors.Companion.getLighterColor
import com.pm.savings.presentation.ui.theme.small12
import com.pm.savings.presentation.ui.theme.small16
import com.pm.savings.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperationItem(
    modifier: Modifier = Modifier,
    operation: Operation,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(operation.color))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = operation.title,
                    style = small16
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = operation.category,
                    style = small12
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${operation.sum} ${operation.currency}",
                style = small12,
                color = textColor.copy(alpha = 0.5f)
            )
        }
    }
}