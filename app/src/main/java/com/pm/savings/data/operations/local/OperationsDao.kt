package com.pm.savings.data.operations.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.pm.savings.data.operations.model.OperationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationsDao {

    @Query("SELECT * FROM operations")
    fun getOperations(): Flow<List<OperationEntity>>

    @Query("SELECT * FROM operations WHERE id = :id")
    suspend fun getOperationById(id: Long): OperationEntity

    @Insert(onConflict = REPLACE)
    suspend fun insertOperation(operation: OperationEntity)

    @Query("DELETE FROM operations WHERE id = :id")
    suspend fun deleteOperationById(id: Long)
}