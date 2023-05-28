package com.pm.savings.domain.operations.repository

import com.pm.savings.domain.operations.model.Operation
import kotlinx.coroutines.flow.Flow

interface OperationsDataSource {
    fun getOperations(): Flow<List<Operation>>
    suspend fun getOperationById(id: Long): Operation
    suspend fun insertOperation(operation: Operation)
    suspend fun deleteOperationById(id: Long)
}