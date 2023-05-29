package com.pm.savings.presentation.stats

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pm.finance.presentation.stats.components.DoughnutChart
import com.pm.savings.R
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.core.Constants.BACKGROUND_COLOR
import com.pm.savings.presentation.core.Constants.HORIZONTAL_PADDING
import com.pm.savings.presentation.core.components.EmptyText
import com.pm.savings.presentation.core.components.SubHeadlineWithAction
import com.pm.savings.presentation.stats.components.CategoryCard
import com.pm.savings.presentation.stats.components.StatsDetailsTopBar
import kotlinx.coroutines.launch
import kotlinx.datetime.Month

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatsScreen(
    initPage: Int,
    stateList: List<StatsState>,
    onEvent: (StatsEvent) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = initPage)

    val isPrevButtonVisible by remember {
        derivedStateOf { pagerState.currentPage > 0 }
    }
    val isNextButtonVisible by remember(stateList.size) {
        derivedStateOf { pagerState.currentPage < stateList.lastIndex }
    }

    if (stateList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BACKGROUND_COLOR)
                .padding(HORIZONTAL_PADDING)
        ) {
            EmptyText(
                modifier = Modifier.fillMaxSize(),
                text = "Make operations\n to see the stats",
                fontSize = 20.sp
            )
        }
    }

    HorizontalPager(
        pageCount = stateList.size,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        StatsScreenContent(
            state = stateList[page],
            isPrevButtonVisible = isPrevButtonVisible,
            isNextButtonVisible = isNextButtonVisible,
            onEvent = { event ->
                when (event) {
                    is StatsEvent.OnCategoryClick -> Unit
                    StatsEvent.OnLeftArrowClick -> coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }

                    StatsEvent.OnRightArrowClick -> coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreenContent(
    state: StatsState,
    isPrevButtonVisible: Boolean,
    isNextButtonVisible: Boolean,
    onEvent: (StatsEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            val monthName =
                Month(state.monthNumber).name.lowercase().replaceFirstChar { it.uppercase() }
            StatsDetailsTopBar(
                modifier = Modifier.padding(horizontal = Constants.TOP_BAR_PADDING),
                onLeftArrowClick = { onEvent(StatsEvent.OnLeftArrowClick) },
                onRightArrowClick = { onEvent(StatsEvent.OnRightArrowClick) },
                text = "${monthName}, ${state.year}",
                isPrevButtonVisible = isPrevButtonVisible,
                isNextButtonVisible = isNextButtonVisible
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BACKGROUND_COLOR)
                .padding(horizontal = HORIZONTAL_PADDING)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                DoughnutChart(
                    categoryWithOperationsUis = state.categoryWithOperationsUis
                )
            }
            item {
                SubHeadlineWithAction(
                    text = stringResource(R.string.statistics),
                    secondText = "",
                    onTextClick = {}
                )
            }
            items(state.categoryWithOperationsUis) { categoryWithOperationsUi ->
                CategoryCard(
                    categoryWithOperationsUi = categoryWithOperationsUi,
                    currency = state.currency,
                    onClick = { onEvent(StatsEvent.OnCategoryClick(categoryWithOperationsUi)) }
                )
            }
        }
    }
}