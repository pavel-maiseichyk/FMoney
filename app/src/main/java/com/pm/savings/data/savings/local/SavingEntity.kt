package com.pm.savings.data.savings.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savings")
data class SavingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String,
    val endSum: Double,
    val savedSum: Double,
    val currency: String,
    val color: Long
)
