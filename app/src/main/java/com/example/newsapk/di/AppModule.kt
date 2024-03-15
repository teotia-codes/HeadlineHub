package com.example.newsapk.di

import android.app.Application
import com.example.newsapk.data.manager.LocalUserMangerImpl
import com.example.newsapk.domain.manager.LocalUserManager
import com.example.newsapk.domain.usecases.AppEntryUseCases
import com.example.newsapk.domain.usecases.ReadAppEntry
import com.example.newsapk.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}