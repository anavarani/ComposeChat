package com.varani.composechat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.varani.composechat.data.local.entities.MessageEntity
import com.varani.composechat.data.local.entities.MessageEntity.Companion.MESSAGE_TABLE
import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(message: MessageEntity)

    @Query("SELECT (SELECT COUNT(*) FROM message WHERE channel_id=:channelId) == 0")
    suspend fun isEmpty(channelId: UUID): Boolean

    @Query("SELECT * from $MESSAGE_TABLE WHERE channel_id=:channelId ORDER by created_at DESC")
    fun getAllMessagesForChat(channelId: UUID): Flow<List<MessageEntity>>
}