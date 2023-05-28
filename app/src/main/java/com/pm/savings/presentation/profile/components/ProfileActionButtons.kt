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
import com.pm.savings.presentation.ui.theme.small16
import com.pm.savings.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileActionButtons(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onPrimaryClick: () -> Unit,
    primaryText: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(
            modifier = Modifier.weight(1f),
            onClick = onBackClick,
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(
                width = 1.dp,
                color = textColor.copy(alpha = 0.5f)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "back",
                    style = small16,
                    color = textColor.copy(alpha = 0.5f)
                )
            }
        }
        Card(
            modifier = Modifier.weight(1f),
            onClick = onPrimaryClick,
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = textColor.copy(alpha = 0.1f)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = primaryText,
                    style = small16,
                    color = textColor.copy(alpha = 0.9f)
                )
            }
        }
    }
}