package com.pm.savings.data.wallet.repository

import com.pm.savings.data.core.local.FinanceDatabase
import com.pm.savings.data.wallet.mapper.toWallet
import com.pm.savings.data.wallet.mapper.toWalletEntity
import com.pm.savings.domain.wallet.model.Wallet
import com.pm.savings.domain.wallet.repository.WalletDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomWalletDataSource(
    db: FinanceDatabase
) : WalletDataSource {

    private val dao = db.walletsDao()

    override fun getWallets(): Flow<List<Wallet>> {
        return dao.getWallets().map { wallets ->
            wallets.map { entity ->
                entity.toWallet()
            }
        }
    }

    override suspend fun getWalletById(id: Long): Wallet {
        return dao.getWalletById(id).toWallet()
    }

    override suspend fun getWalletByName(name: String): Wallet {
        return dao.getWalletByName(name).toWallet()
    }

    override suspend fun insertWallet(wallet: Wallet) {
        dao.insertWallet(wallet.toWalletEntity())
    }

    override suspend fun deleteWalletById(id: Long) {
        dao.deleteWalletById(id)
    }
}