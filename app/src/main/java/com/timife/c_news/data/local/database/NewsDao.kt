package com.timife.c_news.data.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timife.c_news.data.local.entities.ArticleEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(posts: List<ArticleEntity>)

    @Query("SELECT * FROM news WHERE title LIKE '%' || :query || '%'")
    fun pagedArticles(query: String): PagingSource<Int, ArticleEntity>

    @Query("SELECT * FROM news")
    fun pagedArticles(): PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM news")
    suspend fun clearAll()

}