package com.pm.savings.presentation.operation_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.savings.R
import com.pm.savings.domain.category.model.Category
import com.pm.savings.domain.category.repository.CategoryDataSource
import com.pm.savings.domain.core.repository.UserInfoDataSource
import com.pm.savings.domain.core.use_case.FilterOutDigits
import com.pm.savings.domain.core.util.DateTimeUtil
import com.pm.savings.domain.operations.model.Operation
import com.pm.savings.domain.operations.repository.OperationsDataSource
import com.pm.savings.domain.wallet.repository.WalletDataSource
import com.pm.savings.presentation.core.UiEvent
import com.pm.savings.presentation.core.UiText
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.ConfirmDelete
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.DiscardDelete
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.DiscardListAlert
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.GetOperationId
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.OnCategoryClick
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.OnCurrencyClick
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.OnDeleteClick
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.OnDescriptionEnter
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.OnListDialogSave
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.OnSaveClick
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.OnSumEnter
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.OnTypeClick
import com.pm.savings.presentation.operation_details.OperationDetailsEvent.OnWalletClick
import com.pm.savings.presentation.operation_details.SelectedDialogType.CATEGORY
import com.pm.savings.presentation.operation_details.SelectedDialogType.CURRENCY
import com.pm.savings.presentation.operation_details.SelectedDialogType.WALLET
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
class OperationDetailsViewModel @Inject constructor(
    private val walletsDataSource: WalletDataSource,
    private val operationsDataSource: OperationsDataSource,
    private val filterOutDigits: FilterOutDigits,
    private val categoryDataSource: CategoryDataSource,
    private val userInfoDataSource: UserInfoDataSource
) : ViewModel() {

    private lateinit var allCategories: List<Category>
    private lateinit var initialOperation: Operation

    private val _state = MutableStateFlow(OperationDetailsState())
    var state = combine(
        _state,
        walletsDataSource.getWallets(),
        categoryDataSource.getCategories(),
        userInfoDataSource.getMainCurrency()
    ) { state, wallets, categories, mainCurrency ->
        allCategories = categories

        val category =
            categories.find { it.title == state.selectedCategory } ?: categories.firstOrNull()
        state.copy(
            wallets = wallets.map { it.title },
            shownCategories = categories.filter { it.type == state.selectedType }.map { it.title },
            currency = mainCurrency,
            selectedWallet = state.selectedWallet.ifBlank { wallets.first().title },
            selectedCategory = category?.title ?: "",
            color = category?.color ?: 0xFF000000
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), OperationDetailsState())

    private val _uiChannel = Channel<UiEvent>()
    val uiChannel = _uiChannel.receiveAsFlow()

    fun onEvent(event: OperationDetailsEvent) {
        when (event) {
            OnSaveClick -> {
                viewModelScope.launch {
                    try {
                        if (state.value.description.isBlank() || state.value.sum.isBlank()) {
                            _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.fill_all_fields)))
                            return@launch
                        }
                        val operation = Operation(
                            id = state.value.id,
                            title = state.value.description,
                            sum = state.value.sum.toDouble(),
                            type = state.value.selectedType,
                            dateTime = DateTimeUtil.now(),
                            currency = state.value.currency,
                            category = state.value.selectedCategory,
                            wallet = state.value.selectedWallet,
                            color = state.value.color
                        )
                        operationsDataSource.insertOperation(operation)

                        if (::initialOperation.isInitialized && initialOperation != operation) {
                            if (initialOperation.wallet == operation.wallet) {
                                val wallet = walletsDataSource.getWalletByName(initialOperation.wallet)
                                val newSum = wallet.sum + operation.sum * operation.type.sign - initialOperation.sum * initialOperation.type.sign
                                walletsDataSource.insertWallet(wallet.copy(sum = newSum))
                            } else {
                                val initialWallet = walletsDataSource.getWalletByName(initialOperation.wallet)
                                val newInitialWalletSum = initialWallet.sum - initialOperation.sum * initialOperation.type.sign
                                walletsDataSource.insertWallet(initialWallet.copy(sum = newInitialWalletSum))

                                val newWallet = walletsDataSource.getWalletByName(operation.wallet)
                                val newWalletSum = initialWallet.sum + operation.sum * operation.type.sign
                                walletsDataSource.insertWallet(newWallet.copy(sum = newWalletSum))
                            }
                        } else {
                            val wallet = walletsDataSource.getWalletByName(operation.wallet)
                            val newSum = wallet.sum + operation.sum * operation.type.sign
                            walletsDataSource.insertWallet(wallet.copy(sum = newSum))
                        }

                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.saved_successfully)))
                        _uiChannel.send(UiEvent.PopBackStack)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_save_item)))
                        _uiChannel.send(UiEvent.PopBackStack)
                    }
                }
            }

            is OnSumEnter -> {
                val (shouldUpdate, sum) = filterOutDigits.execute(event.text)
                if (shouldUpdate) sum?.let { _state.update { it.copy(sum = sum) } }
            }

            is OnDescriptionEnter -> {
                _state.update { it.copy(description = event.text) }
            }

            ConfirmDelete -> {
                viewModelScope.launch {
                    try {
                        operationsDataSource.deleteOperationById(state.value.id!!)

                        val wallet = walletsDataSource.getWalletByName(_state.value.selectedWallet)
                        val newSum = wallet.sum + _state.value.sum.toDouble() * _state.value.selectedType.sign
                        walletsDataSource.insertWallet(wallet.copy(sum = newSum))

                        _state.update { it.copy(isDeleteDialogShowing = false) }
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.deleted_successfully)))
                        _uiChannel.send(UiEvent.PopBackStack)
                    } catch (e: Exception) {
                        _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_couldn_t_delete_item)))
                    }
                }
            }

            DiscardDelete -> {
                _state.update { it.copy(isDeleteDialogShowing = false) }
            }

            OnCategoryClick -> {
                _state.update {
                    it.copy(
                        listDialogList = state.value.shownCategories,
                        selectedDialogType = CATEGORY,
                        selectedDialogItem = state.value.selectedCategory
                    )
                }
            }

            OnCurrencyClick -> {
                viewModelScope.launch {
                    _uiChannel.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.feature_coming_soon)))
                }
            }

            OnDeleteClick -> {
                _state.update { it.copy(isDeleteDialogShowing = true) }
            }

            is OnTypeClick -> {
                _state.update {
                    it.copy(
                        selectedType = event.type,
                        selectedCategory = allCategories.find { it.type == event.type }?.title ?: ""
                    )
                }
            }

            OnWalletClick -> {
                _state.update {
                    it.copy(
                        listDialogList = state.value.wallets,
                        selectedDialogType = WALLET,
                        selectedDialogItem = state.value.selectedWallet
                    )
                }
            }

            DiscardListAlert -> {
                _state.update {
                    it.copy(
                        listDialogList = emptyList(),
                        selectedDialogType = null,
                        selectedDialogItem = ""
                    )
                }
            }

            is OnListDialogSave -> {
                _state.update {
                    val type = state.value.selectedDialogType
                    it.copy(
                        currency = if (type == CURRENCY) event.value else state.value.currency,
                        selectedWallet = if (type == WALLET) event.value else state.value.selectedWallet,
                        selectedCategory = if (type == CATEGORY) event.value else state.value.selectedCategory,
                        listDialogList = emptyList(),
                        selectedDialogType = null,
                        selectedDialogItem = ""
                    )
                }
            }

            is GetOperationId -> {
                viewModelScope.launch {
                    if (event.id != -1L) {
                        try {
                            val operation = operationsDataSource.getOperationById(event.id!!)
                            initialOperation = operation
                            _state.update {
                                it.copy(
                                    id = operation.id,
                                    sum = operation.sum.toString(),
                                    description = operation.title,
                                    selectedType = operation.type,
                                    currency = operation.currency,
                                    selectedCategory = operation.category,
                                    selectedWallet = operation.wallet
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