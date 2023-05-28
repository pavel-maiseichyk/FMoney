package com.pm.savings.presentation.home

sealed class HomeEvent {
    object OnAddWalletClick : HomeEvent()
    data class OnWalletClick(val id: Long) : HomeEvent()
    object OnAddOperationClick : HomeEvent()
    data class OnOperationClick(val id: Long) : HomeEvent()
}
