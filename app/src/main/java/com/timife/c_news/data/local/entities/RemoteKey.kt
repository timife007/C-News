package com.timife.c_news.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey
    val title: String,
    val nextPage:Int?,
    val prevPage:Int?,
)