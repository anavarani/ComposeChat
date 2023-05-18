package com.varani.composechat.data

import com.varani.composechat.model.Message
import com.varani.composechat.ui.channel.Channel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ChatRepository {

    fun getConversation(channelId: UUID): Flow<List<Message>>

    fun getChannels(): Flow<List<Channel>>

    suspend fun addNewMessageToConversation(channelId: UUID, message: Message)

    suspend fun createChannel(channel: Channel)

    fun getChannelName(channelId: UUID): Flow<String>
}