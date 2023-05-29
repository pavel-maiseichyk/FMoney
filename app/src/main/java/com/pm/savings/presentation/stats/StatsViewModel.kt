package com.pm.savings.presentation.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.savings.domain.category.model.Category
import com.pm.savings.domain.category.repository.CategoryDataSource
import com.pm.savings.domain.core.repository.UserInfoDataSource
import com.pm.savings.domain.core.util.DateTimeUtil
import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.domain.operations.repository.OperationsDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val statsDataSource: CategoryDataSource,
    private val userInfoDataSource: UserInfoDataSource,
    private val operationsDataSource: OperationsDataSource
) : ViewModel() {

    private var stateList = mutableStateListOf<StatsState>()
    private val _stateListFlow = MutableStateFlow(stateList)
    val stateListFlow = _stateListFlow.asStateFlow()

    var initialPage by mutableStateOf(0)
        private set

    init {
        combine(
            userInfoDataSource.getMainCurrency(),
            statsDataSource.getCategories(),
            operationsDataSource.getOperations()
        ) { mainCurrency, categories, operations ->
            val localStatsList = operations
                .filter { it.type == OperationType.Spending }
//                .sortedBy { it.dateTime }
                .groupBy { it.dateTime.monthNumber to it.dateTime.year }
                .map { (pair, pageOperations) ->
                    val totalSum = pageOperations.sumOf { it.sum }
                    val categoryWithOperationsUis = pageOperations
                        .groupBy { it.category }
                        .map { (category, categoryOperations) ->
                            val localSum = categoryOperations.sumOf { it.sum }
                            CategoryWithOperationsUi(
                                category = categories.find { it.title == category } ?: Category(
                                    title = category,
                                    color = 0x80242424,
                                    type = OperationType.Spending
                                ),
                                operations = categoryOperations,
                                sum = localSum,
                                percentage = localSum.toFloat() / totalSum.toFloat() * 100,
                                isExpanded = false
                            )
                        }
                        .sortedByDescending { it.sum }

                    StatsState(
                        monthNumber = pair.first,
                        year = pair.second,
                        sum = totalSum,
                        currency = mainCurrency,
                        categoryWithOperationsUis = categoryWithOperationsUis
                    )
                }

            initialPage = localStatsList.indexOfFirst {
                it.monthNumber to it.year == DateTimeUtil.now().monthNumber to DateTimeUtil.now().year
            }

            stateList = localStatsList.toMutableStateList()
            _stateListFlow.update { stateList }

        }.launchIn(viewModelScope)
    }

    fun onEvent(event: StatsEvent) {
        when (event) {
            is StatsEvent.OnCategoryClick -> {
                stateList[initialPage] =
                    stateList[initialPage].copy(selectedStatsItem = event.statsItem)
            }

            else -> Unit
        }
    }
}