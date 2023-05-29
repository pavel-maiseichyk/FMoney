package com.pm.savings.presentation.category_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.pm.savings.domain.operations.model.OperationType
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.core.components.DetailsTopBar
import com.pm.savings.presentation.core.components.TextUnit
import com.pm.savings.presentation.core.components.dialogs.DeleteDialog
import com.pm.savings.presentation.operation_details.components.OperationTypeButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    state: CategoryDetailsState,
    onEvent: (CategoryDetailsEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            DetailsTopBar(
                modifier = Modifier.padding(horizontal = Constants.TOP_BAR_PADDING),
                title = stringResource(R.string.your_category),
                onBackClick = { onEvent(CategoryDetailsEvent.OnBackClick) },
                onColorChange = { onEvent(CategoryDetailsEvent.OnPaletteClick) },
            )
        }
    ) { paddingValues ->
        DeleteDialog(
            title = stringResource(R.string.delete_this_item),
            isShowing = state.isDeleteDialogShown,
            onDismiss = { onEvent(CategoryDetailsEvent.OnDeleteDialogDiscard) },
            onConfirm = { onEvent(CategoryDetailsEvent.OnDeleteDialogConfirm) },
            color = Color(state.color)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(state.color))
                .padding(horizontal = Constants.HORIZONTAL_PADDING)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                OperationTypeButton(
                    isSelected = state.selectedType == OperationType.Income,
                    color = Color(state.color),
                    onClick = { onEvent(CategoryDetailsEvent.OnTypeSelect(OperationType.Income)) },
                    type = OperationType.Income,
                    modifier = Modifier.weight(1f)
                )
                OperationTypeButton(
                    isSelected = state.selectedType == OperationType.Spending,
                    color = Color(state.color),
                    onClick = { onEvent(CategoryDetailsEvent.OnTypeSelect(OperationType.Spending)) },
                    type = OperationType.Spending,
                    modifier = Modifier.weight(1f)
                )
            }
            TextUnit(
                smallText = stringResource(R.string.title),
                bigText = state.title,
                hint = stringResource(R.string.hint_category_title),
                onValueChange = { onEvent(CategoryDetailsEvent.OnTitleEnter(it)) }
            )
            Spacer(modifier = Modifier.height(4.dp))
            BottomActionButtons(
                shouldShowDelete = state.shouldShowDelete,
                textColor = Color(state.color),
                onDeleteClick = { onEvent(CategoryDetailsEvent.OnDeleteClick) },
                onSaveClick = { onEvent(CategoryDetailsEvent.OnSaveClick) }
            )
        }
    }
}