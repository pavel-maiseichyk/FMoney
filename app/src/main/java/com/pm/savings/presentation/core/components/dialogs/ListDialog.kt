package com.pm.savings.presentation.core.components.dialogs

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pm.savings.presentation.ui.theme.secondaryHeadline
import com.pm.savings.presentation.ui.theme.small14
import com.pm.savings.presentation.ui.theme.small16
import com.pm.savings.presentation.ui.theme.textColor

@Composable
fun ListDialog(
    isShowing: Boolean,
    title: String,
    items: List<String>,
    initialSelected: String,
    onItemChoose: (String) -> Unit,
    onDismiss: () -> Unit,
    color: Color
) {
    var selected by remember(initialSelected) { mutableStateOf(initialSelected) }

    if (isShowing) {
        AlertDialog(
            title = {
                Text(text = title, style = secondaryHeadline, fontWeight = FontWeight.Medium)
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(
                    onClick = { onItemChoose(selected) },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color
                    )
                ) {
                    Text(
                        text = "Confirm",
                        style = small14,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Discard",
                        style = small14,
                        fontWeight = FontWeight.SemiBold,
                        color = color
                    )
                }
            },
            text = {
                Column(Modifier.selectableGroup()) {
                    Log.d("ListDialog", "selected: ${selected}")
                    items.forEach { text ->
                        Log.d("ListDialog", "current: ${text}")
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .selectable(
                                    selected = text == selected,
                                    onClick = { selected = text },
                                    role = Role.RadioButton
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selected),
                                onClick = null,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = color,
                                    unselectedColor = textColor
                                )
                            )
                            Text(
                                text = text,
                                style = small16,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun ListDialogPreview() {
    ListDialog(
        items = listOf("PLN", "USD", "EUR"),
        initialSelected = "PLN",
        title = "Choose currency",
        isShowing = true,
        onDismiss = { },
        onItemChoose = { },
        color = Color(0xFFE74C3C)
    )
}