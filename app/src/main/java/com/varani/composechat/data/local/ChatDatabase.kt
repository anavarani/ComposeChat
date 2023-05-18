package com.varani.composechat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.varani.composechat.data.local.converters.LocalDateTimeConverter
import com.varani.composechat.data.local.converters.UUIDConverter
import com.varani.composechat.data.local.dao.ChatDao
import com.varani.composechat.data.local.dao.MessageDao
import com.varani.composechat.data.local.entities.ChannelEntity
import com.varani.composechat.data.local.entities.MessageEntity

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Database(
    entities = [ChannelEntity::class, MessageEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateTimeConverter::class, UUIDConverter::class)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao

    companion object {
        const val DATABASE_NAME = "chat_database"
    }
}