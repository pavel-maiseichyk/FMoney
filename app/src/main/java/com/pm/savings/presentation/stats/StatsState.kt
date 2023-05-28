package com.pm.savings.presentation.stats

data class StatsState(
    val monthNumber: Int = 0,
    val year: Int = 0,
    val sum: Double = 0.0,
    val currency: String = "",
    val categoryWithOperationsUis: List<CategoryWithOperationsUi> = emptyList(),
    val selectedStatsItem: CategoryWithOperationsUi? = null
)
