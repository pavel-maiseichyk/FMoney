package com.pm.savings.domain.category.model

import com.pm.savings.domain.operations.model.OperationType

data class Category(
    val id: Long? = null,
    val title: String,
    val color: Long,
    val type: OperationType
)