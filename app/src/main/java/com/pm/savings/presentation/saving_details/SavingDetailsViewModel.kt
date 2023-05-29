package com.pm.savings.presentation.saving_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.savings.R
import com.pm.savings.domain.core.repository.UserInfoDataSource
import com.pm.savings.domain.core.use_case.FilterOutDigits
import com.pm.savings.domain.savings.model.Saving
import com.pm.savings.domain.savings.repository.SavingsDataSource
import com.pm.savings.presentation.core.AppColors
import com.pm.savings.presentation.core.UiEvent
import com.pm.savings.presentation.core.UiText
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
class SavingDetailsViewModel @Inject constructor(
    private val savingsDataSource: SavingsDataSource,
    private val userInfoDataSource: UserInfoDataSource,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    private val _state = MutableStateFlow(SavingDetailsState())
    val state = combine(
        _state,
        userInfoDataSource.getMainCurrency()
    ) { state, mainCurrency ->
        state.copy(
            selectedCurrency = mainCurrency
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SavingDetailsState())

    private val _uiChannel = Channel<UiEvent>()
    val uiChannel = _uiChannel.receiveAsFlow()

    fun onEvent(event: SavingDetailsEvent) {
        when (event) {
            SavingDetailsEvent.ConfirmDelete -> {
                viewModelScope.launch {
                    try {
                        savingsDataSource.deleteSavingById(state.value.id!!)
                        _state.update { it.copy(isDeleteDialogShowing = false) }
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.deleted_successfully)))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_delete_item)))
                    } finally {
                        _uiChannel.send(UiEvent.PopBackStack)
                    }
                }
            }

            SavingDetailsEvent.DiscardConfirm -> {
                _state.update { it.copy(isDeleteDialogShowing = false) }
            }

            SavingDetailsEvent.OnDeleteClick -> {
                _state.update { it.copy(isDeleteDialogShowing = true) }
            }

            is SavingDetailsEvent.OnEndSumChange -> {
                val (shouldUpdate, sum) = filterOutDigits.execute(event.text)
                if (shouldUpdate) sum?.let { _state.update { it.copy(endSum = sum) } }
            }

            is SavingDetailsEvent.OnTitleChange -> {
                _state.update { it.copy(title = event.text) }
            }

            is SavingDetailsEvent.GetSavingId -> {
                viewModelScope.launch {
                    try {
                        if (event.id != (-1L)) {
                            val saving = savingsDataSource.getSavingById(event.id!!)

                            _state.update { state ->
                                state.copy(
                                    id = saving.id,
                                    title = saving.title,
                                    endSum = saving.endSum.toString(),
                                    savedSum = saving.savedSum.toString(),
                                    selectedCurrency = saving.currency,
                                    selectedColor = saving.color,
                                )
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_find_item)))
                    }
                }
            }

            SavingDetailsEvent.OnSaveClick -> {
                viewModelScope.launch {
                    val endSum = state.value.endSum.ifBlank {
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.fill_all_fields)))
                        return@launch
                    }.toDouble()
                    val savedSum = state.value.savedSum.ifBlank {
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.fill_all_fields)))
                        return@launch
                    }.toDouble()
                    val title = state.value.title.ifBlank {
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.fill_all_fields)))
                        return@launch
                    }
                    if (endSum < savedSum) {
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_sum_differences)))
                        return@launch
                    }
                    savingsDataSource.insertSaving(
                        Saving(
                            id = state.value.id,
                            title = title,
                            endSum = endSum,
                            savedSum = savedSum,
                            currency = state.value.selectedCurrency,
                            color = state.value.selectedColor
                        )
                    )
                    _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.saved_successfully)))
                    _uiChannel.send(UiEvent.PopBackStack)
                }
            }

            SavingDetailsEvent.OnPaletteClick -> {
                _state.update { it.copy(selectedColor = AppColors.changeColor(it.selectedColor)) }
            }

            is SavingDetailsEvent.OnSavedSumChange -> {
                val (shouldUpdate, sum) = filterOutDigits.execute(event.text)
                if (shouldUpdate) sum?.let { _state.update { it.copy(savedSum = sum) } }
            }

            SavingDetailsEvent.OnCurrencyButtonClick -> {
                viewModelScope.launch {
                    _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.feature_coming_soon)))
                }
            }

            SavingDetailsEvent.OnBackClick -> {
                viewModelScope.launch {
                    _uiChannel.send(UiEvent.PopBackStack)
                }
            }
        }
    }
}