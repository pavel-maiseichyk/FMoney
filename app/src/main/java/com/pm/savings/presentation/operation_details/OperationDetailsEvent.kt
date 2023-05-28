package com.pm.savings.presentation.operation_details

import com.pm.savings.domain.operations.model.OperationType

sealed class OperationDetailsEvent {
    data class GetOperationId(val id: Long?) : OperationDetailsEvent()

    data class OnSumEnter(val text: String) : OperationDetailsEvent()
    data class OnTypeClick(val type: OperationType) : OperationDetailsEvent()
    data class OnDescriptionEnter(val text: String) : OperationDetailsEvent()

    object OnSaveClick : OperationDetailsEvent()
    object OnDeleteClick : OperationDetailsEvent()
    object ConfirmDelete : OperationDetailsEvent()
    object DiscardDelete : OperationDetailsEvent()
    object PopBackStack : OperationDetailsEvent()

    object OnCurrencyClick : OperationDetailsEvent()
    object OnCategoryClick : OperationDetailsEvent()
    object OnWalletClick : OperationDetailsEvent()

    data class OnListDialogSave(val value: String) : OperationDetailsEvent()

    object DiscardListAlert : OperationDetailsEvent()
}