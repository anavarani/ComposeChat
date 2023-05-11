package com.varani.composechat.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.varani.composechat.data.local.entities.MessageEntity.Companion.MESSAGE_TABLE
import com.varani.composechat.model.Message
import java.time.LocalDateTime

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Entity(
    tableName = MESSAGE_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = ChatEntity::class,
            parentColumns = [ChatEntity.CHAT_ID],
            childColumns = [MessageEntity.CHAT_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MESSAGE_ID)
    val id: Int = 0,

    @ColumnInfo(name = CHAT_ID)
    val chatId: Int = 0,

    @ColumnInfo(name = MESSAGE_TYPE_COLUMN)
    val messageType: TYPE,

    @ColumnInfo(name = MESSAGE_TEXT)
    val text: String,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {

    enum class TYPE(val id: Int) {
        SENDER(0),
        RECEIVER(1),
        SECTION_LABEL(2)
    }

    companion object {
        const val MESSAGE_TABLE = "message"
        const val MESSAGE_ID = "_id"
        const val CHAT_ID = "chat_id"
        const val MESSAGE_TYPE_COLUMN = "type"
        const val MESSAGE_TEXT = "text"
        const val CREATED_AT = "created_at"
    }
}

fun List<MessageEntity>.toExternalModel(): List<Message> = this.map {
    when (it.messageType) {
        MessageEntity.TYPE.SENDER -> {
            Message.Sender(
                it.text
            )
        }

        MessageEntity.TYPE.RECEIVER -> {
            Message.Receiver(
                it.text
            )
        }

        MessageEntity.TYPE.SECTION_LABEL -> {
            Message.SectionLabel(
                it.text
            )
        }
    }
}