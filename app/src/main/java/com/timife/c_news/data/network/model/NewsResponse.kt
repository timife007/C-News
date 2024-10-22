package com.timife.c_news.data.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class NewsResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?,
    @SerializedName("articles")
    val articleDtos: List<ArticleDto>
)