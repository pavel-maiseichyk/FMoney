package com.pm.savings.presentation.saving_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.pm.savings.presentation.core.components.DetailsTopBar
import com.pm.savings.presentation.core.components.TextUnit
import com.pm.savings.presentation.core.components.dialogs.DeleteDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavingDetailsScreen(
    state: SavingDetailsState,
    onEvent: (SavingDetailsEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            DetailsTopBar(
                modifier = Modifier.padding(horizontal = Constants.TOP_BAR_PADDING),
                onBackClick = { onEvent(SavingDetailsEvent.OnBackClick) },
                onColorChange = { onEvent(SavingDetailsEvent.OnPaletteClick) },
                title = stringResource(R.string.your_saving)
            )
        }
    ) { paddingValues ->
        DeleteDialog(
            title = stringResource(id = R.string.delete_this_item),
            isShowing = state.isDeleteDialogShowing,
            onDismiss = { onEvent(SavingDetailsEvent.DiscardConfirm) },
            onConfirm = { onEvent(SavingDetailsEvent.ConfirmDelete) },
            color = Color(state.selectedColor)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(state.selectedColor))
                .padding(horizontal = Constants.HORIZONTAL_PADDING)
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            TextUnit(
                smallText = stringResource(id = R.string.title),
                bigText = state.title,
                hint = stringResource(R.string.hilt_saving_title),
                onValueChange = { onEvent(SavingDetailsEvent.OnTitleChange(it)) }
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextUnit(
                smallText = stringResource(id = R.string.all),
                bigText = state.endSum,
                hint = stringResource(id = R.string.hint_sum),
                onValueChange = { onEvent(SavingDetailsEvent.OnEndSumChange(it)) }
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextUnit(
                smallText = stringResource(id = R.string.saved),
                bigText = state.savedSum,
                hint = stringResource(id = R.string.hint_sum),
                onValueChange = { onEvent(SavingDetailsEvent.OnSavedSumChange(it)) }
            )
            Spacer(modifier = Modifier.height(32.dp))
            ButtonUnit(
                smallText = stringResource(id = R.string.currency),
                buttonText = state.selectedCurrency,
                onClick = { onEvent(SavingDetailsEvent.OnCurrencyButtonClick) }
            )
            Spacer(modifier = Modifier.height(48.dp))
            BottomActionButtons(
                modifier = Modifier.fillMaxWidth(),
                textColor = Color(state.selectedColor),
                onDeleteClick = { onEvent(SavingDetailsEvent.OnDeleteClick) },
                onSaveClick = { onEvent(SavingDetailsEvent.OnSaveClick) },
                shouldShowDelete = state.id != null
            )
        }
    }
}