package com.example.newsapk.di

import android.app.Application
import com.example.newsapk.data.manager.LocalUserMangerImpl
import com.example.newsapk.data.remote.NewsApi

import com.example.newsapk.data.repository.NewsRepositoryImpl
import com.example.newsapk.domain.manager.LocalUserManager

import com.example.newsapk.domain.repository.NewsRepository
import com.example.newsapk.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapk.domain.usecases.app_entry.ReadAppEntry
import com.example.newsapk.domain.usecases.app_entry.SaveAppEntry
import com.example.newsapk.domain.usecases.news.GetNews
import com.example.newsapk.domain.usecases.news.NewsUseCases
import com.example.newsapk.domain.usecases.news.SearchNews
import com.example.newsapk.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserMangerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideApiInstance(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }

}