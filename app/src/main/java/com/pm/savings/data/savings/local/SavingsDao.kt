package com.pm.savings.data.savings.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavingsDao {

    @Query("SELECT * FROM savings")
    fun getSavings(): Flow<List<SavingEntity>>

    @Query("SELECT * FROM savings WHERE id = :id")
    suspend fun getSavingById(id: Long): SavingEntity

    @Query("DELETE FROM savings WHERE id = :id")
    suspend fun deleteSavingById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaving(saving: SavingEntity)
}