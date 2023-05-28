package com.pm.savings.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.R
import com.pm.savings.domain.category.model.Category
import com.pm.savings.presentation.ui.theme.small16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onCategoryClick: () -> Unit,
    onDeleteClick: () -> Unit,
    shouldShowDelete: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.weight(1f),
            onClick = onCategoryClick,
            colors = CardDefaults.cardColors(
                containerColor = Color(category.color).copy(0.15f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = category.title,
                    style = small16.copy(color = Color(category.color))
                )
            }
        }
        if (shouldShowDelete) {
            IconButton(
                onClick = onDeleteClick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color(category.color)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete"
                )
            }
        }
    }
}