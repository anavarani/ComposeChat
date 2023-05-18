package com.varani.composechat.ui.conversation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.composechat.data.ChatRepository
import com.varani.composechat.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * Created by Ana Varani on 10/05/2023.
 */
@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val channelId = UUID.fromString(checkNotNull(savedStateHandle["channelId"]))

    val uiState: StateFlow<ConversationUiState> = chatRepository.getConversation(channelId)
        .map { messageList ->
            ConversationUiState(messageList) { msg ->
                sendMessage(msg)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ConversationUiState(emptyList()) {},
        )

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            chatRepository.addNewMessageToConversation(channelId, message)
        }
    }

    fun loadChannelName(): Flow<String> = flow {
        chatRepository.getChannelName(channelId).collect { name ->
            emit(name)
        }
    }
}