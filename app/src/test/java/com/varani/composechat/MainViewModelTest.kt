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
import java.time.Clock
import java.time.LocalDateTime


/**
 * Created by Ana Varani on 11/05/2023.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private var testRepository = TestChatRepository()

    private lateinit var clock: Clock

    private lateinit var sut: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        clock = Clock.systemDefaultZone()

        sut = MainViewModel(
            testRepository,
            clock
        )
    }

    @Test
    fun `when initialised, state is an list with a single section label`() = runTest {
        sut.messageList.test {
            val initialState = awaitItem()
            val label = Message.SectionLabel(LocalDateTime.now(clock).toSectioningLabel())
            assertEquals(listOf(label), initialState)
            advanceTimeBy(11000L)
            assertEquals(1, initialState.size)
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