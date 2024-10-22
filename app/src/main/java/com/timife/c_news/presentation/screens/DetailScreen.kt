package com.timife.c_news.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.timife.c_news.R
import com.timife.c_news.domain.model.Article
import com.timife.c_news.ui.theme.CNewsTheme

@Composable
fun DetailScreen(
    modifier: Modifier,
    article: Article,
    onNavigateBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
                .shadow(elevation = 2.dp)
        ) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(400.dp)
                    .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
            )
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
            ) {
                IconButton(
                    onClick = {
                        onNavigateBack()
                    },
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(10.dp)
                        ),
                    colors = IconButtonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        tint = Color.White,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }

        }
        OwnerInfo(article)
        HorizontalDivider(modifier = Modifier, color = Color.LightGray)
        Text(
            article.description,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(10.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            article.content,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(10.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            stringResource(R.string.author, article.author),
            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(10.dp),
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Composable
fun OwnerInfo(
    article: Article
) {
    Column(
        modifier = Modifier.padding(start = 10.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            article.title,
            style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold),
            modifier = Modifier
        )
        Text(
            text = article.source,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = article.publishedAt,
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            maxLines = 1,
        )
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    CNewsTheme {
        DetailScreen(modifier = Modifier.height(100.dp), article) {

        }
    }
}