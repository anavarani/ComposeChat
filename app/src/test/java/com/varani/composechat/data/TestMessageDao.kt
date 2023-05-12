package com.varani.composechat.data

import com.varani.composechat.data.local.dao.MessageDao
import com.varani.composechat.data.local.entities.MessageEntity
import com.varani.composechat.model.Message
import com.varani.composechat.model.toEntity
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Created by Ana Varani on 11/05/2023.
 */
class TestMessageDao : MessageDao {
    private val messagesFlow: MutableSharedFlow<List<MessageEntity>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun insert(message: MessageEntity) {
        messagesFlow.tryEmit(listOf(message))
    }

    override fun getAllMessagesForChat(chatId: Int): Flow<List<MessageEntity>> {
        return messagesFlow
    }

    fun sendMessageList(messageList: List<Message>) {
        messagesFlow.tryEmit(messageList.map {
            it.toEntity(1)
        })
    }

    override suspend fun isEmpty(chatId: Int): Boolean {
        return true
    }
}