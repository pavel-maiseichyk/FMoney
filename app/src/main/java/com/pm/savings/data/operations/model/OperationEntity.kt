package com.pm.savings.data.operations.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operations")
data class OperationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val title: String,
    val sum: Double,
    val currency: String,
    val category: String,
    val wallet: String,
    val dateTime: Long,
    val type: String,
    val color: Long
)
