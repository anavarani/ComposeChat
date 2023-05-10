package com.varani.composechat.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import com.varani.composechat.R

/**
 * Created by Ana Varani on 10/05/2023.
 */
@Composable
fun MessageBarTextField(
    text: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit,
    modifier: Modifier
) {

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
        placeholder = {
            Text(text = stringResource(id = R.string.message_text_field_placeholder))
        },
        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
            unfocusedBorderColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.disabled),
            trailingIconColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
            disabledTrailingIconColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.disabled),
            textColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
            disabledTextColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.disabled),
        ),
        modifier = modifier
    )
}