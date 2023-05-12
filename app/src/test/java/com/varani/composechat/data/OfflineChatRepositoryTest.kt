package com.varani.composechat.data

import com.varani.composechat.data.local.entities.ChatEntity
import com.varani.composechat.model.Message
import com.varani.composechat.model.toEntity
import com.varani.composechat.toSectioningLabel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockedStatic
import org.mockito.Mockito.mockStatic
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import java.time.LocalDateTime


/**
 * Created by Ana Varani on 11/05/2023.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class OfflineChatRepositoryTest {

    private lateinit var sut: OfflineChatRepository

    private val fixedDateTime = LocalDateTime.now()

    @Spy
    private val testChatDao = TestChatDao()

    @Spy
    private val testMessageDao = TestMessageDao()

    private lateinit var mockLocalDateTime: MockedStatic<LocalDateTime>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        mockLocalDateTime = mockStatic(LocalDateTime::class.java)
        `when`(LocalDateTime.now()).thenReturn(fixedDateTime)

        sut = OfflineChatRepository(
            testChatDao,
            testMessageDao
        )
    }

    @After
    fun close() {
        mockLocalDateTime.close()
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
    fun `when createChat and initial label does not exist, new message is created`() = runTest {
        val chatId = 1

        `when`(testMessageDao.isEmpty(chatId)).thenReturn(true)

        val labelMessage = Message.SectionLabel(fixedDateTime.toSectioningLabel())

        sut.createChat(chatId)

        verify(testChatDao).insert(ChatEntity(chatId))
        verify(testMessageDao).insert(labelMessage.toEntity(chatId))
    }

    @Test
    fun `when createChat and initial label exists, no new message is created`() = runTest {
        val chatId = 1
        `when`(testMessageDao.isEmpty(chatId)).thenReturn(false)

        val labelMessage = Message.SectionLabel(fixedDateTime.toSectioningLabel())

        sut.createChat(chatId)

        verify(testChatDao).insert(ChatEntity(chatId))
        verify(testMessageDao, never()).insert(labelMessage.toEntity(chatId))
    }
}