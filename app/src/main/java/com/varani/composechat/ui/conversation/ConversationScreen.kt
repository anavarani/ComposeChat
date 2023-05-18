package com.varani.composechat.ui.conversation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.varani.composechat.ui.components.ChatSection
import com.varani.composechat.ui.components.ChatTopAppBar
import com.varani.composechat.ui.components.MessageBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationScreen(
    modifier: Modifier = Modifier,
    viewModel: ConversationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val channelName by viewModel.loadChannelName().collectAsStateWithLifecycle("")

    Scaffold(
        modifier = modifier,
        topBar = {
            ChatTopAppBar(
                channelName = channelName
            ) {}
        },
        bottomBar = {
            MessageBottomBar {
                uiState.addMessage(it)
            }
        }
    ) { innerPadding ->
        ChatSection(
            uiState.messages,
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}