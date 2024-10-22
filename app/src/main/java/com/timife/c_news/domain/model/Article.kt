package com.timife.c_news.domain.model

import androidx.annotation.Keep

@Keep
data class Article(
    val title: String,
    val description: String,
    val imageUrl: String,
    val publishedAt: String,
    val source: String,
    val content: String,
    val author:String
)