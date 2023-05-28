package com.pm.savings.domain.savings.repository

import com.pm.savings.domain.savings.model.Saving
import kotlinx.coroutines.flow.Flow

interface SavingsDataSource {
    fun getSavings(): Flow<List<Saving>>
    suspend fun getSavingById(id: Long): Saving
    suspend fun insertSaving(saving: Saving)
    suspend fun deleteSavingById(id: Long)
}