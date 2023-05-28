package com.pm.savings.presentation.operation_details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pm.finance_kmm.android.core.presentation.components.BottomActionButtons
import com.pm.savings.R
import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.core.components.ButtonUnit
import com.pm.savings.presentation.core.components.DetailsTopBar
import com.pm.savings.presentation.core.components.TextUnit
import com.pm.savings.presentation.core.components.dialogs.DeleteDialog
import com.pm.savings.presentation.core.components.dialogs.ListDialog
import com.pm.savings.presentation.operation_details.components.OperationTypeSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperationDetailsScreen(
    state: OperationDetailsState,
    onEvent: (OperationDetailsEvent) -> Unit,
) {
    val context = LocalContext.current
    val toastText = stringResource(id = R.string.feature_coming_soon)

    Scaffold(
        topBar = {
            DetailsTopBar(
                modifier = Modifier.padding(horizontal = Constants.TOP_BAR_PADDING),
                onBackClick = { onEvent(OperationDetailsEvent.PopBackStack) },
                title = stringResource(id = R.string.your_operation),
                isPaletteVisible = false
            )
        }
    ) { paddingValues ->
        DeleteDialog(
            title = stringResource(id = R.string.delete_this_item),
            isShowing = state.isDeleteDialogShowing,
            onDismiss = { onEvent(OperationDetailsEvent.DiscardDelete) },
            onConfirm = { onEvent(OperationDetailsEvent.ConfirmDelete) },
            color = Color(state.color)
        )
        ListDialog(
            isShowing = state.selectedDialogType != null,
            title = stringResource(R.string.choose_option),
            items = state.listDialogList,
            initialSelected = state.selectedDialogItem,
            onItemChoose = { onEvent(OperationDetailsEvent.OnListDialogSave(it)) },
            onDismiss = { onEvent(OperationDetailsEvent.DiscardListAlert) },
            color = Color(state.color)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(state.color))
                .padding(horizontal = Constants.HORIZONTAL_PADDING)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            OperationTypeSection(
                selectedType = state.selectedType,
                color = Color(state.color),
                onTypeClick = { type ->
                    when (type) {
                        OperationType.Transfer, OperationType.Saving -> {
                            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
                        }

                        else -> onEvent(OperationDetailsEvent.OnTypeClick(type))
                    }
                }
            )
            TextUnit(
                smallText = stringResource(id = R.string.sum),
                bigText = state.sum,
                hint = stringResource(id = R.string.hint_sum),
                onValueChange = { onEvent(OperationDetailsEvent.OnSumEnter(it)) }
            )
            ButtonUnit(
                smallText = stringResource(id = R.string.currency),
                buttonText = state.currency,
                onClick = { onEvent(OperationDetailsEvent.OnCurrencyClick) }
            )
            ButtonUnit(
                smallText = stringResource(id = R.string.category),
                buttonText = state.selectedCategory,
                onClick = { onEvent(OperationDetailsEvent.OnCategoryClick) }
            )
            ButtonUnit(
                smallText = stringResource(id = R.string.wallet),
                buttonText = state.selectedWallet,
                onClick = { onEvent(OperationDetailsEvent.OnWalletClick) }
            )
            TextUnit(
                smallText = stringResource(id = R.string.description),
                bigText = state.description,
                hint = stringResource(R.string.hint_description),
                onValueChange = { onEvent(OperationDetailsEvent.OnDescriptionEnter(it)) }
            )
            BottomActionButtons(
                shouldShowDelete = state.id != null,
                textColor = Color(state.color),
                onDeleteClick = { onEvent(OperationDetailsEvent.OnDeleteClick) },
                onSaveClick = { onEvent(OperationDetailsEvent.OnSaveClick) }
            )
        }
    }
}