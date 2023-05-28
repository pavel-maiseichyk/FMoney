package com.pm.savings.presentation.savings

sealed class SavingsEvent {
    object OnAddClick : SavingsEvent()
    data class OnSavingClick(val id: Long) : SavingsEvent()
}

