package com.pm.savings.domain.savings.model

data class Saving(
    val id: Long? = null,
    val title: String,
    val endSum: Double,
    val savedSum: Double,
    val currency: String,
    val color: Long
)