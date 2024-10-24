package com.timife.c_news.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.timife.c_news.data.local.entities.ArticleEntity
import com.timife.c_news.data.network.model.ArticleDto
import com.timife.c_news.domain.model.Article
import com.timife.c_news.domain.utils.formatTimestamp

fun ArticleDto.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        title = title ?: "",
        description = description ?: "",
        imageUrl = urlToImage ?: "",
        publishedAt = publishedAt ?: "",
        source = source?.name ?: "",
        content = content ?: "",
        author = author ?: ""
    )
}


@RequiresApi(Build.VERSION_CODES.O)
fun ArticleEntity.toArticle(): Article {
    return Article(
        title = title,
        description = description,
        imageUrl = imageUrl,
        publishedAt = formatTimestamp(publishedAt),
        source = source,
        content = content,
        author = author
    )
}


