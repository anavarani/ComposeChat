package com.varani.composechat.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.varani.composechat.model.Message

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Composable
fun MessageBottomBar(
    onMessageSent: (Message) -> Unit
) {
    var textField by remember { mutableStateOf(TextFieldValue("")) }
    var isSender by remember { mutableStateOf(true) }

    Surface(modifier = Modifier.shadow(12.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SwitchComposable(isSender) { isSender = !isSender }
            Spacer(modifier = Modifier.size(18.dp))
            MessageBarTextField(
                text = textField,
                onTextChange = { textField = it },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(18.dp))
            SendMessageButton(textField.text.isNotBlank()) {
                onMessageSent(
                    if (isSender) {
                        Message.Sender(textField.text)
                    } else {
                        Message.Receiver(textField.text)
                    }
                )
                textField = TextFieldValue("")
            }
        }
    }
}