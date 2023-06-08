package com.pm.savings.presentation.core.components

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.pm.savings.presentation.ui.theme.headline

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditableText(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isDigits: Boolean
) {
//    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        textStyle = headline.copy(color = Color.White, textAlign = TextAlign.Start),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isDigits) KeyboardType.Number else KeyboardType.Text
        ),
        decorationBox = { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    textAlign = TextAlign.Start,
                    text = hint,
                    style = headline.copy(
                        color = Color.White.copy(alpha = 0.75f),
                        textAlign = TextAlign.Start
                    )
                )
            }
            innerTextField()
        }
    )
}
