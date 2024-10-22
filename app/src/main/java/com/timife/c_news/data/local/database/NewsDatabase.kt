package com.timife.c_news.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timife.c_news.data.local.entities.ArticleEntity
import com.timife.c_news.data.local.entities.RemoteKey

@Database(entities = [ArticleEntity::class, RemoteKey::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}