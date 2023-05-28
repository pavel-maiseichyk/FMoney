package com.pm.savings.presentation.operation_details

import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.presentation.core.AppColors

data class OperationDetailsState(
    val id: Long? = null,

    val selectedType: OperationType = OperationType.Spending,
    val color: Long = AppColors.getRandomColor(),
    val sum: String = "",
    val currency: String = "",
    val shownCategories: List<String> = emptyList(),
    val selectedCategory: String = "",
    val wallets: List<String> = emptyList(),
    val selectedWallet: String = "",
    val description: String = "",

    val isDeleteDialogShowing: Boolean = false,
    val listDialogList: List<String> = emptyList(),
    val selectedDialogItem: String = "",
    val selectedDialogType: SelectedDialogType? = null
)
