package com.varani.composechat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.composechat.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * Created by Ana Varani on 10/05/2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private var timestampJob: Job? = null

    private val _messageList: MutableList<Message> = mutableStateListOf(
        Message.SectionLabel(LocalDateTime.now().toSectioningLabel())
    )
    val messageList = _messageList

    fun sendMessage(message: Message) {
        _messageList.add(message)
        addLabelSectioning(message.createAt)
    }

    private fun addLabelSectioning(lastMessageTimestamp: LocalDateTime) {
        timestampJob?.cancel()
        timestampJob = viewModelScope.launch {
            delay(3000L) // TODO change to 60 * 60 * 100 (1 hour)
            _messageList.add(Message.SectionLabel(lastMessageTimestamp.toSectioningLabel()))
        }
    }
}