package com.varani.composechat.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogComponent(
    message: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    val openDialog = remember { mutableStateOf(true) }
    var name by remember { mutableStateOf("") }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                onDismiss()
            },
            title = {
                Text(text = message)
            },
            text = {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(),
                    value = name,
                    onValueChange = {
                        name = it
                    }
                )
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm(name)
                        openDialog.value = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}