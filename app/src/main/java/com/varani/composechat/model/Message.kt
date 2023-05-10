package com.varani.composechat.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.varani.composechat.R

/**
 * Created by Ana Varani on 09/05/2023.
 */
sealed class Message {

    open lateinit var text: String

    abstract val arrangement: Arrangement.Horizontal
    abstract val bubbleColor: Int
    abstract val bubbleTextColor: Int
    abstract val bubbleShape: Shape

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
}