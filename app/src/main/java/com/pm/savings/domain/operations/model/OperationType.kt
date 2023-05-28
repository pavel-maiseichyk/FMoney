package com.pm.savings.domain.operations.model

enum class OperationType(
    val title: String,
    val color: Long
) {
    Income(
        title = "Income",
        color = 0xFF53D258
    ),
    Spending(
        title = "Spending",
        color = 0xFFE25C5C
    ),
    Saving(
        title = "Saving",
        color = 0xFF5C5CE2
    ),
    Transfer(
        title = "Transfer",
        color = 0xFF057ECE
    );

    companion object {
        fun fromTitle(title: String): OperationType {
            return values().find { it.title.lowercase() == title.lowercase() } ?: Spending
        }
    }
}

