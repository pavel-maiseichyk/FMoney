package com.pm.savings.presentation.wallet_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.savings.R
import com.pm.savings.domain.core.repository.UserInfoDataSource
import com.pm.savings.domain.core.use_case.FilterOutDigits
import com.pm.savings.domain.wallet.model.Wallet
import com.pm.savings.domain.wallet.repository.WalletDataSource
import com.pm.savings.presentation.core.AppColors
import com.pm.savings.presentation.core.UiEvent
import com.pm.savings.presentation.core.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletDetailsViewModel @Inject constructor(
    private val filterOutDigits: FilterOutDigits,
    private val walletDataSource: WalletDataSource,
    private val userInfoDataSource: UserInfoDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(WalletDetailsState())
    var state = combine(_state, userInfoDataSource.getMainCurrency()) { state, currency ->
        state.copy(
            currency = currency
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WalletDetailsState())

    private val _uiChannel = Channel<UiEvent>()
    val uiChannel = _uiChannel.receiveAsFlow()

    fun onEvent(event: WalletDetailsEvent) {
        when (event) {
            is WalletDetailsEvent.GetWalletId -> {
                viewModelScope.launch {
                    if (event.id != -1L) {
                        try {
                            val wallet = walletDataSource.getWalletById(id = event.id)
                            _state.update {
                                it.copy(
                                    id = wallet.id,
                                    title = wallet.title,
                                    sum = wallet.sum.toString(),
                                    currency = wallet.currency,
                                    color = wallet.color,
                                    shouldShowDelete = walletDataSource.getWallets().first().size > 1
                                )
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_find_item)))
                        }
                    }
                }
            }

            WalletDetailsEvent.ConfirmDelete -> {
                viewModelScope.launch {
                    try {
                        walletDataSource.deleteWalletById(id = state.value.id!!)
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.deleted_successfully)))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_delete_item)))
                    } finally {
                        _uiChannel.send(UiEvent.PopBackStack)
                    }
                }
            }

            WalletDetailsEvent.DiscardDelete -> {
                _state.update { it.copy(isDeleteDialogShowing = false) }
            }

            WalletDetailsEvent.OnBackClick -> {
                viewModelScope.launch {
                    _uiChannel.send(UiEvent.PopBackStack)
                }
            }

            WalletDetailsEvent.OnCurrencyButtonCLick -> {
                viewModelScope.launch {
                    _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.feature_coming_soon)))
                }
            }

            WalletDetailsEvent.OnDeleteClick -> {
                _state.update { it.copy(isDeleteDialogShowing = true) }
            }

            WalletDetailsEvent.OnPaletteClick -> {
                _state.update { it.copy(color = AppColors.changeColor(state.value.color)) }
            }

            WalletDetailsEvent.OnSaveClick -> {
                viewModelScope.launch {
                    if (state.value.title.isBlank() || state.value.sum.isBlank()) {
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.fill_all_fields)))
                        return@launch
                    }
                    walletDataSource.getWallets().first().find { it.title == state.value.title }?.let {
                        if (it.id !== state.value.id) {
                            _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.a_wallet_with_this_name_already_exists)))
                            return@launch
                        }
                    }
                    try {
                        walletDataSource.insertWallet(
                            Wallet(
                                id = state.value.id,
                                title = state.value.title,
                                sum = state.value.sum.toDouble(),
                                currency = state.value.currency,
                                color = state.value.color
                            )
                        )
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.saved_successfully)))
                        _uiChannel.send(UiEvent.PopBackStack)
                    } catch (e: Exception) {
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_save_item)))
                    }
                }
            }

            is WalletDetailsEvent.OnSumEnter -> {
                val (shouldUpdate, value) = filterOutDigits.execute(event.text)
                if (shouldUpdate) value?.let { sum -> _state.update { it.copy(sum = sum) } }
            }

            is WalletDetailsEvent.OnTitleEnter -> {
                _state.update { it.copy(title = event.text) }
            }
        }
    }
}