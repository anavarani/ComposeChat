package com.varani.composechat.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varani.composechat.model.Message

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Composable
fun ChatSection(
    messageList: List<Message>,
    modifier: Modifier,
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier.padding(horizontal = 16.dp),
        reverseLayout = true,
    ) {
        items(messageList) { message ->
            Row(
                horizontalArrangement = message.arrangement, modifier = Modifier.fillMaxWidth()
            ) {
                MessageBubble(message = message)
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}