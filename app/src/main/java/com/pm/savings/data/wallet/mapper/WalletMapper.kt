package com.pm.savings.data.wallet.mapper

import com.pm.savings.data.wallet.model.WalletEntity
import com.pm.savings.domain.wallet.model.Wallet


fun WalletEntity.toWallet(): Wallet {
    return Wallet(
        id = id, title = title, sum = sum, currency = currency, color = color
    )
}

fun Wallet.toWalletEntity(): WalletEntity {
    return WalletEntity(
        id = id, title = title, sum = sum, currency = currency, color = color
    )
}