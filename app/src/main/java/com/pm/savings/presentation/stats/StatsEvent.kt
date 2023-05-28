package com.pm.savings.presentation.stats

sealed class StatsEvent {
    object OnLeftArrowClick : StatsEvent()
    object OnRightArrowClick : StatsEvent()
    data class OnCategoryClick(val statsItem: CategoryWithOperationsUi) : StatsEvent()
}
