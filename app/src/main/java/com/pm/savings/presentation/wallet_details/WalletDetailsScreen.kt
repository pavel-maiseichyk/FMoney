package com.pm.savings.presentation.wallet_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pm.finance_kmm.android.core.presentation.components.BottomActionButtons
import com.pm.savings.R
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.core.components.ButtonUnit
import com.pm.savings.presentation.core.components.dialogs.DeleteDialog
import com.pm.savings.presentation.core.Constants.HORIZONTAL_PADDING
import com.pm.savings.presentation.core.components.DetailsTopBar
import com.pm.savings.presentation.core.components.TextUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletDetailsScreen(
    state: WalletDetailsState,
    onEvent: (WalletDetailsEvent) -> Unit
) {
    Scaffold(
        topBar = {
            DetailsTopBar(
                modifier = Modifier.padding(horizontal = Constants.TOP_BAR_PADDING),
                onBackClick = { onEvent(WalletDetailsEvent.OnBackClick) },
                onColorChange = { onEvent(WalletDetailsEvent.OnPaletteClick) },
                title = stringResource(R.string.your_wallet)
            )
        }
    ) { paddingValues ->
        DeleteDialog(
            title = stringResource(R.string.delete_this_item),
            isShowing = state.isDeleteDialogShowing,
            onDismiss = { onEvent(WalletDetailsEvent.DiscardDelete) },
            onConfirm = { onEvent(WalletDetailsEvent.ConfirmDelete) },
            color = Color(state.color)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(state.color))
                .padding(horizontal = HORIZONTAL_PADDING)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TextUnit(
                smallText = stringResource(R.string.title),
                bigText = state.title,
                hint = stringResource(R.string.hint_wallet_title),
                onValueChange = { onEvent(WalletDetailsEvent.OnTitleEnter(it)) },
                isDigits = false
            )
            TextUnit(
                smallText = stringResource(R.string.sum),
                bigText = state.sum,
                hint = stringResource(R.string.hint_sum),
                onValueChange = { onEvent(WalletDetailsEvent.OnSumEnter(it)) },
                isDigits = true
            )
            ButtonUnit(
                smallText = stringResource(R.string.currency),
                buttonText = state.currency,
                onClick = { onEvent(WalletDetailsEvent.OnCurrencyButtonCLick) }
            )
            BottomActionButtons(
                shouldShowDelete = state.shouldShowDelete,
                textColor = Color(state.color),
                onDeleteClick = { onEvent(WalletDetailsEvent.OnDeleteClick) },
                onSaveClick = { onEvent(WalletDetailsEvent.OnSaveClick) }
            )
        }
    }
}