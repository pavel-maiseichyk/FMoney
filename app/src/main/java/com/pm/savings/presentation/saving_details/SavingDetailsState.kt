package com.pm.savings.presentation.saving_details

import com.pm.savings.presentation.core.AppColors

data class SavingDetailsState(
    val id: Long? = null,
    val title: String = "",
    val endSum: String = "",
    val savedSum: String = "",
    val selectedCurrency: String = "",
    val selectedColor: Long = AppColors.getRandomColor(),
    val isDeleteDialogShowing: Boolean = false
)