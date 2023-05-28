package com.pm.savings.presentation.savings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.savings.domain.savings.model.Saving
import com.pm.savings.domain.savings.repository.SavingsDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SavingsViewModel @Inject constructor(
    private val savingsDataSource: SavingsDataSource,
) : ViewModel() {

    var savings: StateFlow<List<Saving>> = savingsDataSource
        .getSavings()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
