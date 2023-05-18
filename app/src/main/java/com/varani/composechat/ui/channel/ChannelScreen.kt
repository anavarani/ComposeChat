package com.varani.composechat.ui.channel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.varani.composechat.R
import com.varani.composechat.ui.components.AlertDialogComponent
import java.util.UUID

@Composable
fun ChannelScreen(
    navigateToConversation: (UUID) -> Unit,
    viewModel: ChannelViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ChannelContent(uiState, navigateToConversation)
}

@Composable
private fun ChannelContent(
    uiState: ChannelUiState,
    navigateToConversation: (UUID) -> Unit,
    scrollState: LazyListState = rememberLazyListState(),
) {

    var showCreateChannelDialog by remember { mutableStateOf(false) }
    if (showCreateChannelDialog) {
        AlertDialogComponent(message = "Create new channel", {}) {
            uiState.addNewChannel(it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        LazyColumn(
            state = scrollState,
            modifier = Modifier.fillMaxSize()
        ) {
            for (channel in uiState.channels) {

                item {
                    Channel(
                        onNavigateToConversation = { id -> navigateToConversation(id) },
                        channelName = channel.name,
                        channelId = channel.id
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { showCreateChannelDialog = true },
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Create new channel"
            )
        }
    }
}

@Composable
fun Channel(
    onNavigateToConversation: (UUID) -> Unit,
    channelName: String,
    channelId: UUID,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(54.dp)
            .clickable { onNavigateToConversation(channelId) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.channel_name, channelName),
            )
        }
    }
}