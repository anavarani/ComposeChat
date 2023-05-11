package com.varani.composechat.data

import com.varani.composechat.model.Message
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Created by Ana Varani on 11/05/2023.
 */
class TestChatRepository : ChatRepository {

    private val messagesFlow: MutableSharedFlow<List<Message>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getConversation(chatId: Int): Flow<List<Message>> {
        return messagesFlow
    }

    override suspend fun addNewMessageToConversation(id: Int, message: Message) {
        messagesFlow.tryEmit(listOf(message))
    }

    override suspend fun createChat(id: Int) {}
}