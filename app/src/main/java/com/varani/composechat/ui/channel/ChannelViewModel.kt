package com.varani.composechat.ui.channel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.composechat.data.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    val uiState: StateFlow<ChannelUiState> = chatRepository.getChannels()
        .map { channels ->
            ChannelUiState(channels) {
                createChannel(it)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChannelUiState(emptyList()) {},
        )

    private fun createChannel(channel: Channel) {
        viewModelScope.launch {
            chatRepository.createChannel(channel)
        }
    }
}