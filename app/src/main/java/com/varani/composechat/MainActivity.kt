package com.varani.composechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.varani.composechat.model.Message
import com.varani.composechat.ui.components.MessageBarTextField
import com.varani.composechat.ui.components.MessageBubble
import com.varani.composechat.ui.components.SendMessageButton
import com.varani.composechat.ui.theme.ComposeChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChatTheme {
                ComposeChatApp(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun ComposeChatApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { ChatTopBar() },
        bottomBar = { ChatMessageBar() }
    ) { innerPadding ->
        ChatSection(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
fun ChatTopBar() {
}

@Composable
fun ChatMessageBar() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        MessageBarTextField(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.size(18.dp))
        SendMessageButton { }
    }
}

@Composable
fun ChatSection(modifier: Modifier) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        reverseLayout = true,
    ) {
        items(messagesStub) { message ->
            Row(
                horizontalArrangement = message.arrangement,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                MessageBubble(message = message)
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun DefaultPreview() {
    ComposeChatTheme {
        ComposeChatApp(Modifier.fillMaxSize())
    }
}

val messagesStub = listOf(
    Message.Sender(text = "Sender"),
    Message.Receiver(text = "Receiver"),
    Message.Receiver(text = "Receiver"),
    Message.Sender(text = "Sender"),
    Message.Receiver(text = "Receiver"),
)