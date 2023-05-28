package com.pm.savings.presentation.savings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pm.savings.domain.savings.model.Saving
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.core.Constants.HORIZONTAL_PADDING
import com.pm.savings.presentation.core.components.EmptyText
import com.pm.savings.presentation.savings.components.SavingCard
import com.pm.savings.presentation.savings.components.SavingsTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavingsScreen(
    savings: List<Saving>,
    onEvent: (SavingsEvent) -> Unit
) {
    Scaffold(
        containerColor = Constants.BACKGROUND_COLOR,
        modifier = Modifier.background(Constants.BACKGROUND_COLOR),
        topBar = {
            SavingsTopBar(
                modifier = Modifier.padding(horizontal = Constants.TOP_BAR_PADDING),
                onAddClick = { onEvent(SavingsEvent.OnAddClick) }
            )
        }
    ) { paddingValues ->
        if (savings.isEmpty()) {
            EmptyText(
                modifier = Modifier.fillMaxSize(),
                text = "Click the plus icon\n to add new saving",
                fontSize = 20.sp
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = HORIZONTAL_PADDING)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(HORIZONTAL_PADDING),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(savings) { saving ->
                    SavingCard(
                        saving = saving,
                        onClick = { onEvent(SavingsEvent.OnSavingClick(saving.id ?: -1L)) }
                    )
                }
            }
        }
    }
}