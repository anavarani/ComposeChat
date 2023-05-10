package com.varani.composechat.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.varani.composechat.model.Message

/**
 * Created by Ana Varani on 10/05/2023.
 */
@Composable
fun MessageBubble(message: Message) {
    Box {
        Surface(
            shape = message.bubbleShape,
            color = colorResource(id = message.bubbleColor),
        ) {
            Text(
                text = message.text,
                color = colorResource(id = message.bubbleTextColor),
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            )
        }

        if (message is Message.Sender) {
            Tick(
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}