package com.pm.savings.domain.wallet.model

data class Wallet(
    val id: Long?,
    val title: String,
    val sum: Double,
    val currency: String,
    val color: Long
)
