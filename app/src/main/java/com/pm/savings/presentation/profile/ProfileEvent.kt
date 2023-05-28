package com.pm.savings.presentation.profile

import android.net.Uri
import com.pm.savings.domain.category.model.Category
import com.pm.savings.domain.operations.model.OperationType

sealed class ProfileEvent {
    data class OnPictureUpdate(val uri: Uri?) : ProfileEvent()
    data class OnNameEnter(val name: String) : ProfileEvent()

    object OnMainCurrencyClick : ProfileEvent()
    data class OnCurrencySelect(val currency: String) : ProfileEvent()
    object OnCurrencySave : ProfileEvent()

    object OnCategoriesClick : ProfileEvent()
    data class OnOperationTypeClick(val type: OperationType) : ProfileEvent()
    data class OnCategoryCLick(val categoryId: Long) : ProfileEvent()
    data class OnDeleteCategoryClick(val category: Category) : ProfileEvent()
    object OnDeleteDialogDiscard : ProfileEvent()
    object OnDeleteDialogConfirm : ProfileEvent()
    object OnAddNewClick : ProfileEvent()

    object OnBackButtonClick : ProfileEvent()
}
