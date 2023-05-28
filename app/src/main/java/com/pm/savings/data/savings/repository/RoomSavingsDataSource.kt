package com.pm.savings.data.savings.repository

import com.pm.savings.domain.savings.model.Saving
import com.pm.savings.domain.savings.repository.SavingsDataSource
import com.pm.savings.data.core.local.FinanceDatabase
import com.pm.savings.data.savings.mapper.toSaving
import com.pm.savings.data.savings.mapper.toSavingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomSavingsDataSource(
    private val db: FinanceDatabase
) : SavingsDataSource {

    private val dao = db.savingsDao()

    override fun getSavings(): Flow<List<Saving>> {
        return dao
            .getSavings()
            .map { list ->
                list.map { entity -> entity.toSaving() }
            }
    }

    override suspend fun getSavingById(id: Long): Saving {
        return dao.getSavingById(id).toSaving()
    }

    override suspend fun insertSaving(saving: Saving) {
        dao.insertSaving(saving.toSavingEntity())
    }

    override suspend fun deleteSavingById(id: Long) {
        dao.deleteSavingById(id)
    }
}