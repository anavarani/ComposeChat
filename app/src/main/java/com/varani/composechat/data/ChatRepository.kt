package com.varani.composechat.data

import com.varani.composechat.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getConversation(chatId: Int): Flow<List<Message>>

    suspend fun addNewMessageToConversation(id: Int, message: Message)

    suspend fun createChat(id: Int)
}