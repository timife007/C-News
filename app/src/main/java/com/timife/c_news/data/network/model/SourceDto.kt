package com.timife.c_news.data.network.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SourceDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)