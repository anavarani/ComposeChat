package com.varani.composechat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.composechat.data.ChatRepository
import com.varani.composechat.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * Created by Ana Varani on 10/05/2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
) : ViewModel() {

    private val chatId = 1 // Mock for simplicity

    val messageList: StateFlow<List<Message>> = chatRepository.getConversation(chatId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    private var timestampJob: Job? = null

    init {
        viewModelScope.launch {
            chatRepository.createChat(chatId)
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            chatRepository.addNewMessageToConversation(chatId, message)
            startTimerForLabelSectioning(message.createAt)
        }
    }

    private fun startTimerForLabelSectioning(lastMessageTimestamp: LocalDateTime) {
        timestampJob?.cancel()
        timestampJob = viewModelScope.launch {
            delay(10000L) // TODO change to 60 * 60 * 1000 (1 hour)
            chatRepository.addNewMessageToConversation(
                chatId,
                Message.SectionLabel(lastMessageTimestamp.toSectioningLabel())
            )
        }
    }
}