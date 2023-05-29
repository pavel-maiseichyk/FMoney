package com.pm.savings.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.savings.domain.core.repository.UserInfoDataSource
import com.pm.savings.domain.operations.repository.OperationsDataSource
import com.pm.savings.domain.savings.repository.SavingsDataSource
import com.pm.savings.domain.wallet.repository.WalletDataSource
import com.pm.savings.presentation.core.combine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource,
    private val walletDataSource: WalletDataSource,
    private val operationsDataSource: OperationsDataSource,
    private val savingsDataSource: SavingsDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    var state = combine(
        _state,
        walletDataSource.getWallets(),
        operationsDataSource.getOperations(),
        savingsDataSource.getSavings(),
        userInfoDataSource.getUserName(),
        userInfoDataSource.getMainCurrency(),
        userInfoDataSource.getProfilePictureUri()
    ) { state, wallets, operations, savings, userName, mainCurrency, uri ->
        state.copy(
            picUri = uri,
            username = userName,
            totalSum = wallets.sumOf { it.sum } + savings.sumOf { it.savedSum },
            mainCurrency = mainCurrency,
            wallets = wallets,
            operations = operations
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeState())
}