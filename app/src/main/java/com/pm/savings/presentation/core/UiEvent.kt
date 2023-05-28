package com.pm.savings.presentation.core

sealed class UiEvent {
    object PopBackStack : UiEvent()
    data class NavigateTo(val route: String) : UiEvent()
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
}
