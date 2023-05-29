package com.pm.savings.presentation.category_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.savings.R
import com.pm.savings.domain.category.model.Category
import com.pm.savings.domain.category.repository.CategoryDataSource
import com.pm.savings.presentation.category_details.CategoryDetailsEvent.GetCategoryId
import com.pm.savings.presentation.category_details.CategoryDetailsEvent.OnDeleteClick
import com.pm.savings.presentation.category_details.CategoryDetailsEvent.OnDeleteDialogConfirm
import com.pm.savings.presentation.category_details.CategoryDetailsEvent.OnDeleteDialogDiscard
import com.pm.savings.presentation.category_details.CategoryDetailsEvent.OnPaletteClick
import com.pm.savings.presentation.category_details.CategoryDetailsEvent.OnSaveClick
import com.pm.savings.presentation.category_details.CategoryDetailsEvent.OnTitleEnter
import com.pm.savings.presentation.category_details.CategoryDetailsEvent.OnTypeSelect
import com.pm.savings.presentation.core.AppColors
import com.pm.savings.presentation.core.UiEvent
import com.pm.savings.presentation.core.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val categoryDataSource: CategoryDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryDetailsState())
    val state = _state.asStateFlow()

    private val _uiChannel = Channel<UiEvent>()
    val uiChannel = _uiChannel.receiveAsFlow()

    fun onEvent(event: CategoryDetailsEvent) {
        when (event) {
            OnDeleteClick -> {
                _state.update { it.copy(isDeleteDialogShown = true) }
            }

            OnDeleteDialogConfirm -> {
                viewModelScope.launch {
                    try {
                        categoryDataSource.deleteCategoryById(state.value.id!!)
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.deleted_successfully)))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_delete_item)))
                    } finally {
                        _uiChannel.send(UiEvent.PopBackStack)
                    }
                }
            }

            OnDeleteDialogDiscard -> {
                _state.update { it.copy(isDeleteDialogShown = false) }
            }

            OnPaletteClick -> {
                val newColor = AppColors.changeColor(state.value.color)
                _state.update { it.copy(color = newColor) }
            }

            OnSaveClick -> {
                viewModelScope.launch {
                    try {
                        categoryDataSource.insertCategory(
                            Category(
                                id = state.value.id,
                                title = state.value.title.trim(),
                                color = state.value.color,
                                type = state.value.selectedType
                            )
                        )
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.saved_successfully)))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_save_item)))
                    } finally {
                        _uiChannel.send(UiEvent.PopBackStack)
                    }
                }
            }

            is OnTitleEnter -> {
                _state.update { it.copy(title = event.title) }
            }

            is OnTypeSelect -> {
                if (event.type != state.value.selectedType)
                    _state.update { it.copy(selectedType = event.type) }
            }

            is GetCategoryId -> {
                viewModelScope.launch {
                    if (event.id != -1L) {
                        try {
                            val category = categoryDataSource.getCategoryById(event.id!!)
                            val shouldShowDelete = categoryDataSource.getCategories().first().filter { it.type == category.type }.size > 1
                            _state.update {
                                it.copy(
                                    id = category.id,
                                    color = category.color,
                                    title = category.title,
                                    selectedType = category.type,
                                    shouldShowDelete = shouldShowDelete
                                )
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_find_item)))
                        }
                    }
                }
            }
            else -> Unit
        }
    }
}