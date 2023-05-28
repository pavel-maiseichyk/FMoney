package com.pm.savings.data.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pm.savings.data.categories.local.CategoriesDao
import com.pm.savings.data.categories.local.CategoryEntity
import com.pm.savings.data.operations.local.OperationsDao
import com.pm.savings.data.operations.model.OperationEntity
import com.pm.savings.data.savings.local.SavingEntity
import com.pm.savings.data.savings.local.SavingsDao
import com.pm.savings.data.wallet.local.WalletsDao
import com.pm.savings.data.wallet.model.WalletEntity

@Database(
    entities = [
        SavingEntity::class,
        OperationEntity::class,
        WalletEntity::class,
        CategoryEntity::class,
    ],
    version = 1
)
abstract class FinanceDatabase : RoomDatabase() {

    abstract fun savingsDao(): SavingsDao
    abstract fun operationsDao(): OperationsDao
    abstract fun walletsDao(): WalletsDao
    abstract fun categoriesDao(): CategoriesDao
}