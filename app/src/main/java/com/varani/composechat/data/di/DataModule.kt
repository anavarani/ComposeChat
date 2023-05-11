package com.varani.composechat.data.di

import com.varani.composechat.data.ChatRepository
import com.varani.composechat.data.OfflineChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindChatRepository(chatRepository: OfflineChatRepository): ChatRepository
}