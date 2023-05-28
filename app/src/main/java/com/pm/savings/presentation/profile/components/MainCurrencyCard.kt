package com.pm.savings.presentation.profile.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pm.savings.presentation.ui.theme.small16
import com.pm.savings.presentation.ui.theme.textColor

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainCurrencyCard(
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
    isExpanded: Boolean,
    currencies: List<String>,
    selected: String,
    onCurrencyClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onCardClick
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(targetState = isExpanded) { state ->
                when (state) {
                    false -> {
                        Text(
                            text = "Main currency",
                            style = small16,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                        )
                    }

                    true -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            currencies.forEach { currency ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    border = BorderStroke(
                                        width = if (selected == currency) 1.dp else 0.dp,
                                        color = if (selected == currency) textColor else Color.White
                                    ),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    ),
                                    onClick = { onCurrencyClick(currency) }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = currency,
                                            style = small16
                                        )
                                    }
                                }
                            }
                            ProfileActionButtons(
                                onBackClick = onBackClick,
                                onPrimaryClick = onSaveClick,
                                primaryText = "save"
                            )
                        }
                    }
                }
            }
        }
    }
}