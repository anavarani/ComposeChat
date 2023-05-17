package com.varani.composechat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.varani.composechat.data.local.entities.MessageEntity
import com.varani.composechat.data.local.entities.MessageEntity.Companion.MESSAGE_TABLE
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(message: MessageEntity)

    @Query("SELECT (SELECT COUNT(*) FROM message WHERE chat_id = :chatId) == 0")
    suspend fun isEmpty(chatId: Int): Boolean

    @Query("SELECT * from $MESSAGE_TABLE WHERE chat_id=:chatId ORDER by created_at DESC")
    fun getAllMessagesForChat(chatId: Int): Flow<List<MessageEntity>>
}