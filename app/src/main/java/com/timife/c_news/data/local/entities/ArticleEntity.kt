package com.timife.c_news.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class ArticleEntity(
    @PrimaryKey
    val title: String,
    val description: String,
    val imageUrl: String,
    val publishedAt: String,
    val source: String,
    val content: String,
    val author: String
)