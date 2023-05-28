package com.pm.savings.domain.wallet.repository

import com.pm.savings.domain.wallet.model.Wallet
import kotlinx.coroutines.flow.Flow

interface WalletDataSource {
    fun getWallets(): Flow<List<Wallet>>
    suspend fun getWalletById(id: Long): Wallet
    suspend fun getWalletByName(name: String): Wallet
    suspend fun insertWallet(wallet: Wallet)
    suspend fun deleteWalletById(id: Long)
}