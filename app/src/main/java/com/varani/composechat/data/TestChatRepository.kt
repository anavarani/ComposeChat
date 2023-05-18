package com.varani.composechat.data

import com.varani.composechat.model.Message
import com.varani.composechat.ui.channel.Channel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.UUID

/**
 * Created by Ana Varani on 11/05/2023.
 */
class TestChatRepository : ChatRepository {

    private val messagesFlow: MutableSharedFlow<List<Message>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getConversation(channelId: UUID): Flow<List<Message>> {
        return messagesFlow
    }

    override fun getChannels(): Flow<List<Channel>> {
        TODO("Not yet implemented")
    }

    override suspend fun addNewMessageToConversation(channelId: UUID, message: Message) {
        messagesFlow.tryEmit(listOf(message))
    }

    override suspend fun createChannel(channel: Channel) {
        TODO("Not yet implemented")
    }

    override fun getChannelName(channelId: UUID): Flow<String> {
        TODO("Not yet implemented")
    }
}