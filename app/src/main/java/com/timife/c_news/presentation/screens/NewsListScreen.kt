package com.timife.c_news.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.timife.c_news.R
import com.timife.c_news.domain.model.Article
import com.timife.c_news.ui.theme.CNewsTheme

@Composable
fun NewsListScreen(
    newsItems: LazyPagingItems<Article>,
    modifier: Modifier = Modifier,
    onArticleClicked: (article: Article) -> Unit,
    messageBus: @Composable (String) -> Unit
) {
    val isRefreshing = newsItems.loadState.refresh is LoadState.Loading
    val isRefreshError = newsItems.loadState.refresh is LoadState.Error
    val isRefreshNotLoading = newsItems.loadState.refresh is LoadState.NotLoading
    val isAppendLoading = newsItems.loadState.append is LoadState.Loading
    val isAppendError = newsItems.loadState.append is LoadState.Error

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            items(count = newsItems.itemCount) { index ->
                val item = newsItems[index]
                item?.let {
                    NewsItem(article = it, onArticleClicked = { onArticleClicked(item) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            //footer loading indicator while appending
            if (isAppendLoading && newsItems.itemCount > 0) {
                item { FooterLoadingItem() }
            }
        }

        when {
            isRefreshing && newsItems.itemCount == 0 -> LoadingItem()
            isRefreshError && newsItems.itemCount == 0 -> ErrorItem()
            isRefreshNotLoading && newsItems.itemCount == 0 ->
                ErrorItem(stringResource(R.string.no_latest_news_found_search_for_latest_news_update))
            isRefreshError && newsItems.itemCount > 0 ->
                messageBus(stringResource(R.string.unknown_error_occurred_please_check_internet_connection))
        }
    }

    // Handles append error separately
    if (isAppendError) {
        messageBus(stringResource(R.string.unknown_error_occurred_please_check_internet_connection))
    }

    // Handles no data scenario when refresh completes with no items
    if (!isRefreshing && !isRefreshError && newsItems.itemCount == 0) {
        ErrorItem(stringResource(R.string.no_latest_news_found_search_for_latest_news_update))
    }

}

@Composable
fun NewsItem(article: Article, onArticleClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                onArticleClicked()
            },
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
fun ErrorItem(errorMessage: String = "Unknown error occurred, please try again") {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.outline_error_outline_24),
                contentDescription = "",
                modifier = Modifier.size(60.dp)
            )
            Text(
                errorMessage,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                minLines = 2
            )
        }
    }
}

@Composable
fun FooterLoadingItem() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        CircularProgressIndicator(
            modifier = Modifier.size(15.dp)
        )
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
            article,
            {}
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
