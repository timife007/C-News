package com.timife.c_news.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.timife.c_news.data.local.database.NewsDao
import com.timife.c_news.data.local.database.NewsDatabase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class NewsDbTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var newsDao: NewsDao
    private lateinit var db: NewsDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).allowMainThreadQueries().build()
        newsDao = db.newsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun writeAndQueryArticles() = runTest {
        val articles = TestData.articles
        newsDao.upsertAll(articles)
        val pagingSource = newsDao.pagedArticles("Yoga").load(
            PagingSource.LoadParams.Refresh(0,2, false)
        ) as PagingSource.LoadResult.Page

        assert(pagingSource.data.size == 1)
        assert(pagingSource.data[0] == articles[3])
    }

    @Test
    @Throws(Exception::class)
    fun clearAllArticles() = runTest {
        val articles = TestData.articles
        newsDao.upsertAll(articles)
        val pagingSource = newsDao.pagedArticles().load(PagingSource.LoadParams.Refresh(0,10, false)
        ) as PagingSource.LoadResult.Page
        assert(pagingSource.data.size == 10)
        newsDao.clearAll()
        val newPagingSource = newsDao.pagedArticles().load(PagingSource.LoadParams.Refresh(0,2, false)
        ) as PagingSource.LoadResult.Page
        assert(newPagingSource.data.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun ensureAccurateResponseEvenWithLargerLoadSize() = runTest {
        val articles = TestData.articles.dropLast(4)
        newsDao.upsertAll(articles)
        val pagingSource = newsDao.pagedArticles().load(PagingSource.LoadParams.Refresh(0,10, false)
        ) as PagingSource.LoadResult.Page
        assert(pagingSource.data.size == 6)
    }
}


