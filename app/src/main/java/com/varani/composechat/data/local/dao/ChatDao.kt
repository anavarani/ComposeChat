package com.varani.composechat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.varani.composechat.data.local.entities.ChannelEntity
import com.varani.composechat.data.local.entities.ChannelEntity.Companion.CHANNEL_TABLE
import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(chat: ChannelEntity)

    @Query("SELECT * from $CHANNEL_TABLE ORDER by created_at DESC")
    fun getAllChannels(): Flow<List<ChannelEntity>>

    @Query("SELECT channel_name FROM $CHANNEL_TABLE WHERE id=:channelId")
    fun getChannelName(channelId: UUID): Flow<String>
}