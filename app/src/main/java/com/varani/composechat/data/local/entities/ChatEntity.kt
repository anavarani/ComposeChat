package com.varani.composechat.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.varani.composechat.data.local.entities.ChatEntity.Companion.CHAT_TABLE
import java.time.LocalDateTime

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Entity(tableName = CHAT_TABLE)
data class ChatEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = CHAT_ID)
    val id: Int = 0,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        const val CHAT_TABLE = "chat"
        const val CHAT_ID = "_id"
        const val CREATED_AT = "created_at"
    }
}