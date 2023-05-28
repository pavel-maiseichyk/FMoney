package com.pm.savings.data.wallet.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pm.savings.data.wallet.model.WalletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletsDao {

    @Query("SELECT * FROM wallets")
    fun getWallets(): Flow<List<WalletEntity>>

    @Query("SELECT * FROM wallets WHERE id = :id")
    suspend fun getWalletById(id: Long): WalletEntity

    @Query("SELECT * FROM wallets WHERE title = :name")
    suspend fun getWalletByName(name: String): WalletEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallet(wallet: WalletEntity)

    @Query("DELETE FROM wallets WHERE id = :id")
    suspend fun deleteWalletById(id: Long)
}