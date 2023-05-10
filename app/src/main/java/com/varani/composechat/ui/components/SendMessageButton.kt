package com.varani.composechat.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.varani.composechat.R

/**
 * Created by Ana Varani on 10/05/2023.
 */
@Composable
fun SendMessageButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        modifier = Modifier
            .size(48.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.send_icon),
            contentDescription = null,
            tint = Color.White
        )
    }
}