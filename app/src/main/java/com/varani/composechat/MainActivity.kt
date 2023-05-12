package com.varani.composechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.varani.composechat.ui.components.ChatSection
import com.varani.composechat.ui.components.ChatTopAppBar
import com.varani.composechat.ui.components.MessageBottomBar
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
    val conversation by viewModel.messageList.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = { ChatTopAppBar {} },
        bottomBar = { MessageBottomBar() }
    ) { innerPadding ->
        ChatSection(
            conversation,
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun DefaultPreview() {
    ComposeChatTheme {
        ComposeChatApp(Modifier.fillMaxSize())
    }
}