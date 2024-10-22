package com.timife.c_news.data.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SourceDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)