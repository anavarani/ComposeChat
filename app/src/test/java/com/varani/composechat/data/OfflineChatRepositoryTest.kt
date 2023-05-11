package com.varani.composechat.data

import com.varani.composechat.data.local.entities.ChatEntity
import com.varani.composechat.model.Message
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Spy

/**
 * Created by Ana Varani on 11/05/2023.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class OfflineChatRepositoryTest {

    private lateinit var sut: OfflineChatRepository

    @Spy
    private val testChatDao = TestChatDao()

    private val testMessageDao = TestMessageDao()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        sut = OfflineChatRepository(
            testChatDao,
            testMessageDao
        )
    }

    @Test
    fun `when list is sent to dao, flow is updated`() = runTest {
        val messageList = listOf(
            Message.Sender("Hi"),
            Message.Receiver("Hello"),
        )
        testMessageDao.sendMessageList(messageList)
        val messages = sut.getConversation(1).first()

        assertEquals(messageList, messages)
    }

    @Test
    fun `when new message, insert is called`() = runTest {
        val chatId = 1
        val message = Message.Sender("Test")
        sut.addNewMessageToConversation(chatId, message)
        val messages = sut.getConversation(1).first()

        Pair(message, messages[0]).run {
            assertEquals(first.text, second.text)
            assertEquals(first.arrangement, second.arrangement)
            assertEquals(first.bubbleColor, second.bubbleColor)
            assertEquals(first.bubbleShape, second.bubbleShape)
            assertEquals(first.bubbleTextColor, second.bubbleTextColor)
        }
    }

    @Test
    fun `verify createChat is called`() = runTest {
        val chatId = 1
        sut.createChat(chatId)

        verify(testChatDao).insert(ChatEntity(chatId))
    }
}