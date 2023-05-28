package com.pm.savings.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pm.savings.R
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.core.components.EmptyText
import com.pm.savings.presentation.core.components.SubHeadlineWithAction
import com.pm.savings.presentation.home.components.BalanceBox
import com.pm.savings.presentation.home.components.HomeTopBar
import com.pm.savings.presentation.home.components.OperationItem
import com.pm.savings.presentation.home.components.WalletItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        containerColor = Constants.BACKGROUND_COLOR,
        modifier = Modifier
            .background(Constants.BACKGROUND_COLOR),
        topBar = {
            HomeTopBar(
                uri = state.picUri,
                username = state.username,
                modifier = Modifier.padding(horizontal = Constants.HORIZONTAL_PADDING)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                BalanceBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Constants.HORIZONTAL_PADDING),
                    balance = "${state.totalSum} ${state.mainCurrency}"
                )
            }
            item {
                SubHeadlineWithAction(
                    modifier = Modifier.padding(horizontal = Constants.HORIZONTAL_PADDING),
                    text = stringResource(R.string.wallets),
                    secondText = stringResource(R.string.add),
                    onTextClick = { onEvent(HomeEvent.OnAddWalletClick) }
                )
            }
            item {
                if (state.wallets.isEmpty()) {
                    EmptyText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Constants.HORIZONTAL_PADDING),
                        text = stringResource(R.string.empty_wallets)
                    )
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = Constants.HORIZONTAL_PADDING)
                    ) {
                        items(state.wallets) { wallet ->
                            WalletItem(
                                wallet = wallet,
                                onClick = { onEvent(HomeEvent.OnWalletClick(wallet.id!!)) }
                            )
                        }
                    }
                }
            }
            item {
                SubHeadlineWithAction(
                    modifier = Modifier.padding(horizontal = Constants.HORIZONTAL_PADDING),
                    text = stringResource(R.string.operations),
                    secondText = stringResource(id = R.string.add),
                    onTextClick = { onEvent(HomeEvent.OnAddOperationClick) }
                )
            }
            if (state.operations.isEmpty()) {
                item {
                    EmptyText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Constants.HORIZONTAL_PADDING),
                        text = stringResource(R.string.empty_operations)
                    )
                }
            } else {
                items(state.operations) { operation ->
                    OperationItem(
                        modifier = Modifier.padding(horizontal = Constants.HORIZONTAL_PADDING),
                        onClick = { onEvent(HomeEvent.OnOperationClick(operation.id ?: -1L)) },
                        operation = operation
                    )
                }
            }
        }
    }
}