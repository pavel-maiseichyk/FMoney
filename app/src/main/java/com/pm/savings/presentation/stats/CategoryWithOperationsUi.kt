package com.pm.savings.presentation.stats

import com.pm.savings.domain.category.model.Category
import com.pm.savings.domain.operations.model.Operation

data class CategoryWithOperationsUi(
    val category: Category,
    val operations: List<Operation>,
    val sum: Double,
    val percentage: Float,
    val isExpanded: Boolean = false
)
