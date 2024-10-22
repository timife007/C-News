package com.timife.c_news.data.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ArticleDto(
    @SerializedName("source")
    val source: SourceDto?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?
)