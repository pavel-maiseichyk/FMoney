package com.pm.savings.presentation.profile.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pm.savings.domain.category.model.Category
import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.presentation.ui.theme.small16

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CategoriesCard(
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
    isExpanded: Boolean,
    selectedType: OperationType,
    onTypeClick: (OperationType) -> Unit,
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    onDeleteCategoryClick: (Category) -> Unit,
    onBackClick: () -> Unit,
    onAddNewClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            disabledContainerColor = Color.White
        ),
        onClick = onCardClick,
        enabled = !isExpanded
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(targetState = isExpanded) { state ->
                when (state) {
                    false -> {
                        Text(
                            text = "Categories",
                            style = small16,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                        )
                    }

                    true -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 20.dp)
                        ) {
                            ProfileTypeButtons(
                                selectedType = selectedType,
                                onTypeClick = onTypeClick
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            AnimatedContent(targetState = selectedType) { state ->
                                when (state) {
                                    OperationType.Spending -> {
                                        val filtered =
                                            categories.filter { it.type === OperationType.Spending }
                                        val shouldShowDelete = remember(filtered.size) {
                                            filtered.size > 1
                                        }
                                        Column {
                                            filtered.forEach { category ->
                                                CategoryItem(
                                                    category = category,
                                                    onCategoryClick = { onCategoryClick(category) },
                                                    onDeleteClick = { onDeleteCategoryClick(category) },
                                                    shouldShowDelete = shouldShowDelete
                                                )
                                            }
                                        }
                                    }

                                    OperationType.Income -> {
                                        val filtered =
                                            categories.filter { it.type === OperationType.Income }
                                        val shouldShowDelete = remember(filtered.size) {
                                            filtered.size > 1
                                        }
                                        Column {
                                            filtered.forEach { category ->
                                                CategoryItem(
                                                    category = category,
                                                    onCategoryClick = { onCategoryClick(category) },
                                                    onDeleteClick = { onDeleteCategoryClick(category) },
                                                    shouldShowDelete = shouldShowDelete
                                                )
                                            }
                                        }
                                    }

                                    else -> Unit
                                }

                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            ProfileActionButtons(
                                onBackClick = onBackClick,
                                onPrimaryClick = onAddNewClick,
                                primaryText = "add new"
                            )
                        }
                    }
                }
            }
        }
    }
}