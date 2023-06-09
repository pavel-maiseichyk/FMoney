package com.pm.savings.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pm.savings.R
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.core.Constants.HORIZONTAL_PADDING
import com.pm.savings.presentation.core.components.dialogs.DeleteDialog
import com.pm.savings.presentation.profile.components.CategoriesCard
import com.pm.savings.presentation.profile.components.MainCurrencyCard
import com.pm.savings.presentation.profile.components.ProfilePic
import com.pm.savings.presentation.profile.components.ProfileTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit
) {

//    val photoPickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia(),
//        onResult = { uri -> onEvent(ProfileEvent.OnPictureUpdate(uri)) }
//    )

    val context = LocalContext.current
    val toastText = stringResource(id = R.string.feature_coming_soon)

    Scaffold(
        topBar = { ProfileTopBar() }
    ) { paddingValues ->
        DeleteDialog(
            title = "Delete category?",
            isShowing = state.isDeleteDialogShowing,
            onDismiss = { onEvent(ProfileEvent.OnDeleteDialogDiscard) },
            onConfirm = { onEvent(ProfileEvent.OnDeleteDialogConfirm) },
            color = Color(state.selectedCategory?.color ?: 0)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Constants.BACKGROUND_COLOR)
                .padding(HORIZONTAL_PADDING)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfilePic(
                uri = state.picUri,
                onClick = {
                    Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
//                    photoPickerLauncher.launch(
//                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                    )
                }
            )
            MainCurrencyCard(
                onCardClick = {
                    Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
//                    onEvent(ProfileEvent.OnMainCurrencyClick)
                },
                isExpanded = state.selectedToggle == ProfileToggle.MAIN_CURRENCY,
                currencies = state.currencies,
                selected = state.selectedCurrency,
                onBackClick = { onEvent(ProfileEvent.OnBackButtonClick) },
                onCurrencyClick = { onEvent(ProfileEvent.OnCurrencySelect(it)) },
                onSaveClick = { onEvent(ProfileEvent.OnCurrencySave) }
            )
            CategoriesCard(
                onCardClick = { onEvent(ProfileEvent.OnCategoriesClick) },
                isExpanded = state.selectedToggle == ProfileToggle.CATEGORIES,
                selectedType = state.selectedCategoryType,
                onTypeClick = { onEvent(ProfileEvent.OnOperationTypeClick(it)) },
                categories = state.categories,
                onCategoryClick = { onEvent(ProfileEvent.OnCategoryCLick(it.id ?: -1L)) },
                onDeleteCategoryClick = { onEvent(ProfileEvent.OnDeleteCategoryClick(it)) },
                onBackClick = { onEvent(ProfileEvent.OnBackButtonClick) },
                onAddNewClick = { onEvent(ProfileEvent.OnAddNewClick) }
            )
        }
    }
}