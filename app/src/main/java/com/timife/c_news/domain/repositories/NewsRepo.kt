package com.timife.c_news.domain.repositories

import androidx.paging.PagingData
import com.timife.c_news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    fun getPaginatedArticles(
        query: String
    ): Flow<PagingData<Article>>
}