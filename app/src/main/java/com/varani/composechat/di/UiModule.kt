package com.varani.composechat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.Clock

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    fun provideClock(): Clock = Clock.systemDefaultZone()
}