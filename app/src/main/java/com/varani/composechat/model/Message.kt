package com.varani.composechat.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.varani.composechat.R
import com.varani.composechat.data.local.entities.MessageEntity
import java.time.LocalDateTime

/**
 * Created by Ana Varani on 09/05/2023.
 */
sealed class Message {

    open lateinit var text: String

    abstract val arrangement: Arrangement.Horizontal
    abstract val bubbleColor: Int
    abstract val bubbleTextColor: Int
    abstract val bubbleShape: Shape
    val createAt: LocalDateTime = LocalDateTime.now()

    data class Sender(override var text: String) : Message() {
        override val arrangement = Arrangement.End
        override val bubbleColor = R.color.sender_bubble_color
        override val bubbleTextColor = R.color.on_sender_bubble_color
        override val bubbleShape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 12.dp)
    }

    data class Receiver(override var text: String) : Message() {
        override val arrangement = Arrangement.Start
        override val bubbleColor = R.color.receiver_bubble_color
        override val bubbleTextColor = R.color.on_receiver_bubble_color
        override val bubbleShape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 0.dp)
    }

    data class SectionLabel(override var text: String) : Message() {
        override val arrangement = Arrangement.Center
        override val bubbleColor = android.R.color.transparent
        override val bubbleTextColor = R.color.timestamp_text_color
        override val bubbleShape = RoundedCornerShape(0.dp)
    }
}

fun Message.toEntity(chatId: Int) = run {
    MessageEntity(
        chatId = chatId,
        messageType = this.toEnumType(),
        text = text
    )
}

fun Message.toEnumType() = run {
    when (this) {
        is Message.Sender -> MessageEntity.TYPE.SENDER
        is Message.Receiver -> MessageEntity.TYPE.RECEIVER
        is Message.SectionLabel -> MessageEntity.TYPE.SECTION_LABEL
    }
}