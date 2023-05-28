package com.pm.savings.presentation.wallet_details

sealed class WalletDetailsEvent {
    data class GetWalletId(val id: Long) : WalletDetailsEvent()

    object OnBackClick : WalletDetailsEvent()
    object OnPaletteClick : WalletDetailsEvent()
    data class OnTitleEnter(val text: String) : WalletDetailsEvent()
    data class OnSumEnter(val text: String) : WalletDetailsEvent()
    object OnCurrencyButtonCLick : WalletDetailsEvent()

    object OnDeleteClick : WalletDetailsEvent()
    object DiscardDelete : WalletDetailsEvent()
    object ConfirmDelete : WalletDetailsEvent()

    object OnSaveClick : WalletDetailsEvent()
}
