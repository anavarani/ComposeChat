package com.varani.composechat

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.composechat.data.ChatRepository
import com.varani.composechat.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ana Varani on 10/05/2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
) : ViewModel() {

    private val chatId = 1 // TODO replace hardcoded value

    val uiState: StateFlow<ConversationUiState> = chatRepository.getConversation(chatId)
        .map {
            ConversationUiState(chatId.toString(), it) { msg ->
                sendMessage(msg)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ConversationUiState(chatId.toString(), emptyList()) {},
        )

    init {
        viewModelScope.launch {
            chatRepository.createChat(chatId)
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            chatRepository.addNewMessageToConversation(chatId, message)
        }
    }
}

class ConversationUiState(
    val channelName: String,
    initialMessages: List<Message>,
    val onMessageSent: (Message) -> Unit
) {

    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
    val messages: List<Message> = _messages

    fun addMessage(msg: Message) {
        _messages.add(0, msg)
        onMessageSent(msg)
    }
}