package com.pm.savings.domain.operations.model

import kotlinx.datetime.LocalDateTime

data class Operation(
    val id: Long? = null,
    val title: String,
    val sum: Double,
    val currency: String,
    val category: String,
    val wallet: String,
    val dateTime: LocalDateTime,
    val type: OperationType,
    val color: Long
)
