package com.varani.composechat.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.varani.composechat.R

/**
 * Created by Ana Varani on 10/05/2023.
 */
@Composable
fun SendMessageButton(
    isEnabled: Boolean,
    onSendMessageClick: () -> Unit
) {
    IconButton(
        onClick = onSendMessageClick,
        enabled = isEnabled,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.38f),
        ),
        modifier = Modifier
            .size(48.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.send_icon),
            contentDescription = null,
        )
    }
}