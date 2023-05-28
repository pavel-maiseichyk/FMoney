package com.pm.savings.presentation.profile

import android.net.Uri
import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.domain.category.model.Category

data class ProfileState(
    val picUri: Uri? = null,
    val userName: String = "",
    val selectedToggle: ProfileToggle? = null,
    val currencies: List<String> = emptyList(),
    val selectedCurrency: String = "",
    val selectedCategoryType: OperationType = OperationType.Spending,
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val isDeleteDialogShowing: Boolean = false
)
