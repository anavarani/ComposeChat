package com.varani.composechat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.varani.composechat.data.local.entities.ChatEntity

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(chat: ChatEntity)
}