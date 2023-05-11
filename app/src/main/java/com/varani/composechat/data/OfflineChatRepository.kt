package com.varani.composechat.data

import com.varani.composechat.data.local.dao.ChatDao
import com.varani.composechat.data.local.dao.MessageDao
import com.varani.composechat.data.local.entities.ChatEntity
import com.varani.composechat.data.local.entities.toExternalModel
import com.varani.composechat.model.Message
import com.varani.composechat.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineChatRepository @Inject constructor(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao,
) : ChatRepository {
    override fun getConversation(chatId: Int): Flow<List<Message>> =
        messageDao.getAllMessagesForChat(chatId).map {
            it.toExternalModel()
        }

    override suspend fun addNewMessageToConversation(id: Int, message: Message) {
        messageDao.insert(message.toEntity(id))
    }

    override suspend fun createChat(id: Int) {
        chatDao.insert(ChatEntity(id))
    }
}