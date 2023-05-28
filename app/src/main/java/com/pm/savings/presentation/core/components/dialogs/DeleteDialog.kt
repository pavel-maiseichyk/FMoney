package com.pm.savings.presentation.core.components.dialogs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.ui.theme.secondaryHeadline
import com.pm.savings.presentation.ui.theme.small14
import com.pm.savings.presentation.ui.theme.textColor

@Composable
fun DeleteDialog(
    title: String,
    isShowing: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    color: Color
) {
    if (isShowing) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = title, style = secondaryHeadline, fontWeight = FontWeight.Medium)
            },
            text = {
                Text(
                    text = "This cannot be undone.",
                    style = small14,
                    color = textColor.copy(0.75f)
                )
            },
            confirmButton = {
                Button(
                    onClick = onConfirm,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color
                    )
                ) {
                    Text(
                        text = "Delete",
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
            containerColor = Constants.BACKGROUND_COLOR,
            textContentColor = textColor,
            shape = RoundedCornerShape(24.dp)
        )
    }
}

@Preview
@Composable
fun DeleteDialogPreview() {
    DeleteDialog(
        title = "Delete saving?",
        isShowing = true,
        onDismiss = { },
        onConfirm = { },
        color = Color(0xFFE74C3C)
    )
}