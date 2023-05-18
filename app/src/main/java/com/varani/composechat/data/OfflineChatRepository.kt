package com.varani.composechat.data

import com.varani.composechat.data.local.dao.ChatDao
import com.varani.composechat.data.local.dao.MessageDao
import com.varani.composechat.data.local.entities.toEntity
import com.varani.composechat.data.local.entities.toExternalModel
import com.varani.composechat.model.Message
import com.varani.composechat.model.toEntity
import com.varani.composechat.ui.channel.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class OfflineChatRepository @Inject constructor(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao,
) : ChatRepository {
    override fun getConversation(channelId: UUID): Flow<List<Message>> =
        messageDao.getAllMessagesForChat(channelId).map {
            it.toExternalModel()
        }

    override suspend fun addNewMessageToConversation(channelId: UUID, message: Message) {
        messageDao.insert(message.toEntity(channelId))
    }

    override suspend fun createChannel(channel: Channel) {
        chatDao.insert(channel.toEntity())
    }

    override fun getChannelName(channelId: UUID): Flow<String> = chatDao.getChannelName(channelId)

    override fun getChannels(): Flow<List<Channel>> {
        return chatDao.getAllChannels().map {
            it.toExternalModel()
        }
    }
}