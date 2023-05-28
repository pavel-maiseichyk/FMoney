package com.pm.savings.data.operations.mapper

import com.pm.savings.data.operations.model.OperationEntity
import com.pm.savings.domain.core.util.DateTimeUtil.toEpochMillis
import com.pm.savings.domain.operations.model.Operation
import com.pm.savings.domain.operations.model.OperationType
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun OperationEntity.toOperation(): Operation {
    return Operation(
        id = id,
        title = title,
        sum = sum,
        currency = currency,
        type = OperationType.valueOf(type),
        dateTime = Instant.fromEpochMilliseconds(dateTime)
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        category = category,
        wallet = wallet,
        color = color
    )
}

fun Operation.toOperationEntity(): OperationEntity {
    return OperationEntity(
        id = id,
        title = title,
        sum = sum,
        currency = currency,
        type = type.title,
        dateTime = dateTime.toEpochMillis(),
        category = category,
        wallet = wallet,
        color = color
    )
}