package com.pm.savings.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.savings.domain.category.repository.CategoryDataSource
import com.pm.savings.domain.core.repository.UserInfoDataSource
import com.pm.savings.presentation.core.UiEvent
import com.pm.savings.presentation.core.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val categoryDataSource: CategoryDataSource,
    private val userInfoDataSource: UserInfoDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    var state = combine(
        _state,
        userInfoDataSource.getUserName(),
        userInfoDataSource.getMainCurrency(),
        userInfoDataSource.getProfilePictureUri(),
        categoryDataSource.getCategories()
    ) { state, userName, mainCurrency, uri, categories ->
        state.copy(
            picUri = uri,
            userName = userName,
            selectedCurrency = mainCurrency,
            categories = categories
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileState())

    private val _uiChannel = Channel<UiEvent>()
    val uiChannel = _uiChannel.receiveAsFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnBackButtonClick -> {
                _state.update { it.copy(selectedToggle = null) }
            }

            ProfileEvent.OnCategoriesClick -> {
                _state.update { it.copy(selectedToggle = ProfileToggle.CATEGORIES) }
            }

            ProfileEvent.OnCurrencySave -> {
                viewModelScope.launch {
                    userInfoDataSource.updateMainCurrency(currency = state.value.selectedCurrency)
                    onEvent(ProfileEvent.OnBackButtonClick)
                }
            }

            is ProfileEvent.OnCurrencySelect -> {
                _state.update { it.copy(selectedCurrency = event.currency) }
            }

            is ProfileEvent.OnDeleteCategoryClick -> {
                _state.update {
                    it.copy(
                        selectedCategory = event.category,
                        isDeleteDialogShowing = true
                    )
                }
            }

            ProfileEvent.OnDeleteDialogConfirm -> {
                viewModelScope.launch {
                    categoryDataSource.deleteCategoryById(id = state.value.selectedCategory!!.id!!)
                    _state.update { it.copy(isDeleteDialogShowing = false) }
                }
            }

            ProfileEvent.OnDeleteDialogDiscard -> {
                _state.update { it.copy(isDeleteDialogShowing = false) }
            }

            is ProfileEvent.OnOperationTypeClick -> {
                if (state.value.selectedCategoryType != event.type)
                    _state.update {
                        it.copy(
                            selectedCategoryType = event.type,
                        )
                    }
            }

            ProfileEvent.OnMainCurrencyClick -> {
                _state.update { it.copy(selectedToggle = ProfileToggle.MAIN_CURRENCY) }
            }

            is ProfileEvent.OnNameEnter -> {
                _state.update { it.copy(userName = it.userName) }
            }

            ProfileEvent.OnAddNewClick -> {
                viewModelScope.launch {
                    _uiChannel.send(UiEvent.NavigateTo(Routes.CATEGORY_DETAILS))
                }
            }

            is ProfileEvent.OnCategoryCLick -> {
                viewModelScope.launch {
                    _uiChannel.send(UiEvent.NavigateTo(Routes.CATEGORY_DETAILS))
                }
            }

            is ProfileEvent.OnPictureUpdate -> {
                viewModelScope.launch {
                    userInfoDataSource.setProfilePictureUri(event.uri ?: return@launch)
                }
            }
        }
    }
}