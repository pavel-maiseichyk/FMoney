package com.pm.savings.data.operations.repository

import com.pm.savings.data.core.local.FinanceDatabase
import com.pm.savings.data.operations.mapper.toOperation
import com.pm.savings.data.operations.mapper.toOperationEntity
import com.pm.savings.domain.operations.model.Operation
import com.pm.savings.domain.operations.repository.OperationsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomOperationsDataSource(
    db: FinanceDatabase
) : OperationsDataSource {
    private val dao = db.operationsDao()

    override fun getOperations(): Flow<List<Operation>> {
        return dao.getOperations().map { list -> list.map { it.toOperation() } }
    }

    override suspend fun getOperationById(id: Long): Operation {
        return dao.getOperationById(id).toOperation()
    }

    override suspend fun insertOperation(operation: Operation) {
        return dao.insertOperation(operation.toOperationEntity())
    }

    override suspend fun deleteOperationById(id: Long) {
        return dao.deleteOperationById(id)
    }
}