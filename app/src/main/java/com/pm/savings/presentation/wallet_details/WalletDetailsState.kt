package com.pm.savings.presentation.wallet_details

import com.pm.savings.presentation.core.AppColors

data class WalletDetailsState(
    val id: Long? = null,
    val title: String = "",
    val sum: String = "",
    val currency: String = "",
    val color: Long = AppColors.getRandomColor(),
    val isDeleteDialogShowing: Boolean = false,
    val shouldShowDelete: Boolean = false
)
