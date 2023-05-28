package com.pm.savings.domain.category.model

import com.pm.savings.domain.operations.model.Operation

data class CategoryWithOperations(
    val category: Category,
    val operations: List<Operation>
)
