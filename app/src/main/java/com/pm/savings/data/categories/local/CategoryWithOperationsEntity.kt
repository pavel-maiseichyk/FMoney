package com.pm.savings.data.categories.local

import androidx.room.Embedded
import androidx.room.Relation
import com.pm.savings.data.operations.model.OperationEntity

data class CategoryWithOperationsEntity(
    @Embedded val category: CategoryEntity,
    @Relation(parentColumn = "title", entityColumn = "category")
    val operations: List<OperationEntity>
)
