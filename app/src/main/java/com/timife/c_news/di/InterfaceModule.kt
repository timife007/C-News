package com.timife.c_news.di

import com.timife.c_news.data.local.database.NewsDatabase
import com.timife.c_news.data.network.NewsApi
import com.timife.c_news.data.repositories.NewsRepoImpl
import com.timife.c_news.domain.repositories.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object InterfaceModule {

    @Provides
    @Singleton
    fun provideNewsRepo(
        db: NewsDatabase,
        api: NewsApi
    ): NewsRepo {
        return NewsRepoImpl(
            db = db,
            newsApi = api
        )
    }
}