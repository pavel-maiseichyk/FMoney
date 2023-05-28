package com.pm.savings.presentation.home

import android.net.Uri
import com.pm.savings.domain.operations.model.Operation
import com.pm.savings.domain.wallet.model.Wallet

data class HomeState(
    val picUri: Uri? = null,
    val username: String = "",
    val totalSum: Double = 0.0,
    val mainCurrency: String = "",
    val wallets: List<Wallet> = emptyList(),
    val operations: List<Operation> = emptyList()
)
