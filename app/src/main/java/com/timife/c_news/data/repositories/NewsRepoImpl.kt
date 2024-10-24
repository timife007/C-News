package com.timife.c_news.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.timife.c_news.data.local.database.NewsDatabase
import com.timife.c_news.data.mappers.toArticle
import com.timife.c_news.data.network.NewsApi
import com.timife.c_news.data.paging.NewsPagingMediator
import com.timife.c_news.domain.model.Article
import com.timife.c_news.domain.repositories.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NewsRepoImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val db: NewsDatabase
): NewsRepo {

    override fun getPaginatedArticles(
        query: String
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 15),
            remoteMediator = NewsPagingMediator(
                q = query,
                database = db,
                api = newsApi
            ),
            pagingSourceFactory = {
                if (query.isBlank() || query.isEmpty()) {
                    db.newsDao().pagedArticles()
                }else{
                    db.newsDao().pagedArticles(query)
                }
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toArticle()
            }
        }.flowOn(Dispatchers.IO)
    }
}