package com.varani.composechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.varani.composechat.model.Message
import com.varani.composechat.ui.components.MessageBarTextField
import com.varani.composechat.ui.components.MessageBubble
import com.varani.composechat.ui.components.SendMessageButton
import com.varani.composechat.ui.theme.ComposeChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeChatApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = { ChatTopBar {} },
        bottomBar = { ChatMessageBar() }
    ) { innerPadding ->
        ChatSection(
            viewModel.messageList,
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(onNavigationClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back to previous screen",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(60.dp)
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Android",
                    fontSize = 20.sp
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier.shadow(6.dp)
    )
}

@Composable
fun ChatMessageBar(
    viewModel: MainViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Surface(modifier = Modifier.shadow(12.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            MessageBarTextField(
                text = text,
                onTextChange = { text = it },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(18.dp))
            SendMessageButton(text.text.isNotBlank()) {
                viewModel.sendMessage(Message.Sender(text.text.toAnnotatedString()))
                text = TextFieldValue("")
            }
        }
    }
}

@Composable
fun ChatSection(
    messageList: List<Message>,
    modifier: Modifier,
) {
    val listState = rememberLazyListState()
    LaunchedEffect(messageList.size) {
        listState.animateScrollToItem(messageList.size)
    }
    LazyColumn(
        state = listState,
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        items(messageList) { message ->
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