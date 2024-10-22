package com.timife.c_news.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.timife.c_news.domain.model.Article
import com.timife.c_news.ui.theme.CNewsTheme

@Composable
fun NewsListScreen(
    newsItems: LazyPagingItems<Article>,
    modifier: Modifier = Modifier,
    messageBus: @Composable (String) -> Unit
) {

    val isRefreshing = newsItems.loadState.refresh is LoadState.Loading

    if (isRefreshing && newsItems.itemCount == 0) {
        LoadingItem()
    }

    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        if (newsItems.loadState.refresh is LoadState.Error) {
            val error = (newsItems.loadState.refresh as LoadState.Error).error
            if (newsItems.itemCount == 0) {
                ErrorItem()
            } else {
                messageBus(
                    "Unknown error occurred, please check internet connection"
                )
            }
        }

        LazyColumn(
            modifier = modifier
        ) {
            items(
                count = newsItems.itemCount,
                key = newsItems.itemKey(),
                contentType = newsItems.itemContentType(
                )
            ) { index ->
                val item = newsItems[index]
                item?.let {
                    NewsItem(article = it)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }


            // Handle Loading state (when new pages are being loaded).
            newsItems.apply {
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            FooterLoadingItem()
                        }
                    }

                    is LoadState.Error -> {
                        val e = loadState.append as LoadState.Error
                        item {
                            messageBus("Unknown error occurred, please check internet connection")
                        }
                    }

                    is LoadState.NotLoading -> {
                    }
                }
            }
        }

    }

}

@Composable
fun NewsItem(article: Article) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AsyncImage(
            article.imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Transparent,
                )
                .clip(RoundedCornerShape(8.dp))
                .fillMaxHeight()
                .size(110.dp)

        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = article.source,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = article.author,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = article.publishedAt,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ErrorItem() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Unknown error occurred, please try again")
    }
}

@Composable
fun FooterLoadingItem() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingItem() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    CNewsTheme {
        NewsItem(
            article
        )

    }
}

val article = Article(
    title = "What Training Do VolleyBall Players Need?",
    description = "What Training Do VolleyBall Players Need?",
    imageUrl = "https://www.sportingnews.com/wp-content/uploads/2023/0",
    publishedAt = "23rd March, 2020",
    source = "Sporting News",
    content = "What Training Do VolleyBall Players Need?",
    author = "Timife"
)
