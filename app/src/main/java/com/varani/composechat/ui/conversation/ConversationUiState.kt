package com.varani.composechat.ui.conversation

import androidx.compose.runtime.toMutableStateList
import com.varani.composechat.model.Message

class ConversationUiState(
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