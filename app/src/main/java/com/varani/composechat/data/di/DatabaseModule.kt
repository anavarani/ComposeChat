package com.varani.composechat.data.di

import android.content.Context
import androidx.room.Room
import com.varani.composechat.data.local.ChatDatabase
import com.varani.composechat.data.local.ChatDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ChatDatabase {
        return Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideChatDao(database: ChatDatabase) = database.chatDao()

    @Provides
    @Singleton
    fun provideMessageDao(database: ChatDatabase) = database.messageDao()
}