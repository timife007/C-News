package com.timife.c_news.presentation.navigation

import com.timife.c_news.domain.model.Article
import kotlinx.serialization.Serializable


@Serializable
data object NewsListRoute

@Serializable
data class NewsDetailRoute(
    val article:Article
)