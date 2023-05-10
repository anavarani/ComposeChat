package com.varani.composechat

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.varani.composechat.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ana Varani on 10/05/2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _messageList = messagesStub.toMutableStateList()
    val messageList = _messageList

    fun sendMessage(message: Message) {
        _messageList.add(message)
    }
}

sealed class ChatUiState {
    object Loading : ChatUiState()
    data class Success(val messageList: List<Message>) : ChatUiState()
    data class Error(val errorMessage: String) : ChatUiState()
}

val messagesStub = mutableListOf(
    Message.Sender(text = "Sender"),
    Message.Receiver(text = "Receiver"),
    Message.Receiver(text = "Receiver"),
    Message.Sender(text = "Sender"),
    Message.Receiver(text = "Receiver"),
)