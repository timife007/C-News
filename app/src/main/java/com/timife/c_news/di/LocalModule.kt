package com.timife.c_news.di

import android.content.Context
import androidx.room.Room
import com.timife.c_news.data.local.database.NewsDao
import com.timife.c_news.data.local.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule{

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase{
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase) = db.newsDao()
}