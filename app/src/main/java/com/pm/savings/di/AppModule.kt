package com.pm.savings.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.pm.savings.data.categories.repository.RoomCategoryDataSource
import com.pm.savings.data.core.local.FinanceDatabase
import com.pm.savings.data.core.repository.DataStoreUserInfoDataSource
import com.pm.savings.data.operations.repository.RoomOperationsDataSource
import com.pm.savings.data.savings.repository.RoomSavingsDataSource
import com.pm.savings.data.wallet.repository.RoomWalletDataSource
import com.pm.savings.domain.category.repository.CategoryDataSource
import com.pm.savings.domain.core.repository.UserInfoDataSource
import com.pm.savings.domain.core.use_case.FilterOutDigits
import com.pm.savings.domain.operations.repository.OperationsDataSource
import com.pm.savings.domain.savings.repository.SavingsDataSource
import com.pm.savings.domain.wallet.repository.WalletDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DATABASE_NAME = "finance_database"
    private const val PREFERENCES_NAME = "finance_preferences"

    @Provides
    @Singleton
    fun provideFinanceDatabase(app: Application): FinanceDatabase {
        return Room.databaseBuilder(
            app,
            FinanceDatabase::class.java,
            DATABASE_NAME
        )
            .createFromAsset("database/finance_database.db")
            .build()
    }

    @Provides
    @Singleton
    fun providePreferencesDataSource(app: Application): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { app.preferencesDataStoreFile(PREFERENCES_NAME) }
        )
    }

    @Provides
    @Singleton
    fun provideFilterOutDigits(): FilterOutDigits {
        return FilterOutDigits()
    }

    @Provides
    @Singleton
    fun provideSavingsDataSource(db: FinanceDatabase): SavingsDataSource {
        return RoomSavingsDataSource(db)
    }

    @Provides
    @Singleton
    fun provideStatsDataSource(db: FinanceDatabase): CategoryDataSource {
        return RoomCategoryDataSource(db)
    }

    @Provides
    @Singleton
    fun provideWalletDataSource(db: FinanceDatabase): WalletDataSource {
        return RoomWalletDataSource(db)
    }

    @Provides
    @Singleton
    fun provideOperationsDataSource(db: FinanceDatabase): OperationsDataSource {
        return RoomOperationsDataSource(db)
    }

    @Provides
    @Singleton
    fun provideUserInfoDataSource(prefs: DataStore<Preferences>): UserInfoDataSource {
        return DataStoreUserInfoDataSource(prefs)
    }
}