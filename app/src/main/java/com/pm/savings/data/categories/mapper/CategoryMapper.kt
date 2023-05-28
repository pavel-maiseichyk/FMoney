package com.pm.savings.data.categories.mapper

import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.data.categories.local.CategoryEntity
import com.pm.savings.data.categories.local.CategoryWithOperationsEntity
import com.pm.savings.data.operations.mapper.toOperation
import com.pm.savings.domain.category.model.Category
import com.pm.savings.domain.category.model.CategoryWithOperations

fun CategoryWithOperationsEntity.toCategoryWithOperations() = CategoryWithOperations(
    category = category.toCategory(),
    operations = operations.map { it.toOperation() }
)

fun CategoryEntity.toCategory() = Category(
    id = id,
    title = title,
    color = color,
    type = OperationType.fromTitle(type)
)

fun Category.toEntity() = CategoryEntity(
    id = id,
    title = title,
    color = color,
    type = type.title
)