package com.varani.composechat.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Composable
fun SwitchComposable(isChecked: Boolean, onCheckChange: (Boolean) -> Unit) {
    Switch(
        checked = isChecked,
        onCheckedChange = onCheckChange,
        colors = SwitchDefaults.colors(
            checkedIconColor = MaterialTheme.colorScheme.primary,
            uncheckedTrackColor = MaterialTheme.colorScheme.tertiaryContainer,
        )
    )
}