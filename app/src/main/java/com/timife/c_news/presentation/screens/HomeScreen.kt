package com.timife.c_news.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.timife.c_news.domain.model.Article
import com.timife.c_news.presentation.viewmodels.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    modifier:Modifier,
    viewModel: NewsViewModel = hiltViewModel(),
    onArticleClicked:(article:Article) -> Unit,
    messageBus: @Composable (String) -> Unit
){
    val pagedData = viewModel.news.collectAsLazyPagingItems()
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val onActiveChange = { }
        val colors1 = SearchBarDefaults.colors()
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = searchQuery.value,
                    onQueryChange = {
                        viewModel.onSearchQueryChanged(it)
                    },
                    onSearch = { },
                    expanded = false,
                    onExpandedChange = {},
                    enabled = true,
                    placeholder = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                    },
                    trailingIcon = null,
                    colors = colors1.inputFieldColors,
                    interactionSource = null,
                )
            },
            expanded = false,
            onExpandedChange = {},
            modifier = Modifier.fillMaxWidth(0.95f),
            shape = SearchBarDefaults.inputFieldShape,
            colors = colors1,
            tonalElevation = SearchBarDefaults.TonalElevation,
            shadowElevation = SearchBarDefaults.ShadowElevation,
            windowInsets = SearchBarDefaults.windowInsets,
            content = {},
        )
        NewsListScreen(
            newsItems = pagedData,
            modifier = Modifier,
            onArticleClicked = onArticleClicked,
            messageBus = {
                messageBus(it)
            }
        )
    }
}