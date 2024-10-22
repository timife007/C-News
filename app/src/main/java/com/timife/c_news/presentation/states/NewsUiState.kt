package com.timife.c_news.presentation.states

import androidx.paging.PagingData
import com.timife.c_news.domain.model.Article

sealed class NewsUiState {
    data class Success(val news: PagingData<Article>) : NewsUiState()
    data class Error(val exception: Throwable) : NewsUiState()
    data object Loading : NewsUiState()

}