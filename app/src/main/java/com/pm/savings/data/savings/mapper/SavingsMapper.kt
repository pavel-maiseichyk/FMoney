package com.pm.savings.data.savings.mapper

import com.pm.savings.domain.savings.model.Saving
import com.pm.savings.data.savings.local.SavingEntity

fun SavingEntity.toSaving(): Saving {
    return Saving(
        id = id,
        title = title,
        endSum = endSum,
        savedSum = savedSum,
        currency = currency,
        color = color
    )
}

fun Saving.toSavingEntity(): SavingEntity {
    return SavingEntity(
        id = id,
        title = title,
        endSum = endSum,
        savedSum = savedSum,
        currency = currency,
        color = color
    )
}