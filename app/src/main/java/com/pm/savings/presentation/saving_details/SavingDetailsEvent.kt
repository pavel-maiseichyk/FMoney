package com.pm.savings.presentation.saving_details

sealed class SavingDetailsEvent {
    data class GetSavingId(val id: Long?) : SavingDetailsEvent()
    data class OnTitleChange(val text: String) : SavingDetailsEvent()
    data class OnEndSumChange(val text: String) : SavingDetailsEvent()
    data class OnSavedSumChange(val text: String) : SavingDetailsEvent()
    object OnBackClick : SavingDetailsEvent()
    object OnPaletteClick : SavingDetailsEvent()
    object OnDeleteClick : SavingDetailsEvent()
    object ConfirmDelete : SavingDetailsEvent()
    object DiscardConfirm : SavingDetailsEvent()
    object OnCurrencyButtonClick : SavingDetailsEvent()
    object OnSaveClick : SavingDetailsEvent()
}
