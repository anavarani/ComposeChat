package com.varani.composechat

import app.cash.turbine.test
import com.varani.composechat.data.TestChatRepository
import com.varani.composechat.model.Message
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


/**
 * Created by Ana Varani on 11/05/2023.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private var testRepository = TestChatRepository()

    private lateinit var sut: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        sut = MainViewModel(
            testRepository
        )
    }

    @Test
    fun `when initialised, state is an empty list`() = runTest {
        sut.messageList.test {
            val initialState = awaitItem()
            assertEquals(emptyList<Message>(), initialState)
        }
    }

    @Test
    fun `when message is sent by Sender, it is added to the message list`() = runTest {
        sut.messageList.test {
            val senderMessage = Message.Sender("Hi")
            val labelMessage = Message.SectionLabel(senderMessage.createAt.toSectioningLabel())

            awaitItem() // initial
            sut.sendMessage(senderMessage)
            assertEquals(senderMessage, awaitItem()[0]) // TestRepo emits a list with the message
            advanceTimeBy(10000L)
            assertEquals(labelMessage, awaitItem()[0]) // Label is sent automatically after delay
        }
    }

    @Test
    fun `when message is sent by Receiver, it is added to the message list`() = runTest {
        sut.messageList.test {
            val receiverMessage = Message.Receiver("Hello")
            val labelMessage = Message.SectionLabel(receiverMessage.createAt.toSectioningLabel())

            awaitItem() // initial
            sut.sendMessage(receiverMessage)
            assertEquals(receiverMessage, awaitItem()[0]) // TestRepo emits a list with the message
            advanceTimeBy(10000L)
            assertEquals(labelMessage, awaitItem()[0]) // Label is sent automatically after delay
        }
    }
}