package com.timife.c_news.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.timife.c_news.data.local.database.NewsDatabase
import com.timife.c_news.data.local.entities.ArticleEntity
import com.timife.c_news.data.local.entities.RemoteKey
import com.timife.c_news.data.mappers.toArticleEntity
import com.timife.c_news.data.network.NewsApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsPagingMediator(
    private val database: NewsDatabase,
    private val api: NewsApi,
    private val q:String? = null
): RemoteMediator<Int, ArticleEntity>(){
    private val remoteKeyDao = database.remoteKeyDao()
    private val dao = database.newsDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        if (q == null) {
                            remoteKeyDao.getRemoteKeyByQuery("")
                        } else  remoteKeyDao.getRemoteKeyByQuery(q)
                    }
                    if(remoteKey.nextPage == null){
                        return MediatorResult.Success(true)
                    }
                    remoteKey.nextPage
                }
            }

            val data = if (q == null) {
                api.getTopHeadlines(
                    page = loadKey,
                    pageSize = state.config.pageSize
                ).body()?.articleDtos
            }
            else {
                api.getEverything(query = q, page = loadKey, pageSize =  state.config.pageSize)
                    .body()?.articleDtos
            }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.clearAll()
                    remoteKeyDao.deleteAllRemoteKeys(q ?: "")
                }

                val nextPage = if(data?.isEmpty() == true){
                    null
                }else{
                    loadKey + 1
                }

                val articleEntities = data?.map {
                    it.toArticleEntity()
                }

                remoteKeyDao.insertOrReplace(
                    if (q == null) RemoteKey(
                        title = "",
                        nextPage = nextPage,
                        prevPage = null
                    ) else RemoteKey(
                        title = q,
                        nextPage = nextPage,
                        prevPage = null
                    )
                )
                articleEntities?.let {
                    dao.upsertAll(it)
                }
            }
            MediatorResult.Success(
                endOfPaginationReached = data?.isEmpty() == true
            )

        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}