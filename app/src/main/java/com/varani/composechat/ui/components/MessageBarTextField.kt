package com.varani.composechat.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import com.varani.composechat.R

/**
 * Created by Ana Varani on 10/05/2023.
 */
@OptIn(ExperimentalMaterial3Api::class)
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
            focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
            unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
            disabledTrailingIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
            textColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 1f),
            disabledTextColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.38f),
        ),
        modifier = modifier
    )
}