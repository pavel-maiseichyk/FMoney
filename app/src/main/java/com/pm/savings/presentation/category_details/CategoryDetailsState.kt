package com.pm.savings.presentation.category_details

import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.presentation.core.AppColors

data class CategoryDetailsState(
    val id: Long? = null,
    val color: Long = AppColors.getRandomColor(),
    val title: String = "",
    val selectedType: OperationType = OperationType.Spending,
    val isDeleteDialogShown: Boolean = false,
    val shouldShowDelete: Boolean = false
)
