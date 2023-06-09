package com.pm.savings.domain.operations.model

enum class OperationType(
    val title: String,
    val color: Long,
    val sign: Int
) {
    Income(
        title = "Income",
        color = 0xFF53D258,
        sign = 1
    ),
    Spending(
        title = "Spending",
        color = 0xFFE25C5C,
        sign = -1
    ),
    Saving(
        title = "Saving",
        color = 0xFF5C5CE2,
        sign = -1
    ),
    Transfer(
        title = "Transfer",
        color = 0xFF057ECE,
        sign = 0
    );

    companion object {
        fun fromTitle(title: String): OperationType {
            return values().find { it.title.lowercase() == title.lowercase() } ?: Spending
        }
    }
}

