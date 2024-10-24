package com.timife.c_news.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState

// Fake PagingSource
class FakePagingSource<T : Any>(
    private val data: List<T>
) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return LoadResult.Page(data, prevKey = null, nextKey = null)
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null
}