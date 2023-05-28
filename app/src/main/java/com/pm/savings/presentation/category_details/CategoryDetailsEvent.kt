package com.pm.savings.presentation.category_details

import com.pm.savings.domain.operations.model.OperationType

sealed class CategoryDetailsEvent {
    data class GetCategoryId(val id: Long?): CategoryDetailsEvent()
    object OnBackClick : CategoryDetailsEvent()
    object OnPaletteClick : CategoryDetailsEvent()
    data class OnTitleEnter(val title: String) : CategoryDetailsEvent()
    data class OnTypeSelect(val type: OperationType) : CategoryDetailsEvent()
    object OnSaveClick : CategoryDetailsEvent()
    object OnDeleteClick : CategoryDetailsEvent()
    object OnDeleteDialogDiscard : CategoryDetailsEvent()
    object OnDeleteDialogConfirm : CategoryDetailsEvent()
}
