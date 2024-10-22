package com.timife.c_news.presentation

import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.timife.c_news.R
import com.timife.c_news.domain.model.Article
import com.timife.c_news.ui.theme.CNewsTheme

@Composable
fun DetailScreen(
    modifier: Modifier,
    navController: NavHostController,
    article: Article
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

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
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.fruit),
                contentDescription = "",
                modifier = Modifier.height(400.dp)
            )
            Row(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
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
            Text(
                article.title,
                style = MaterialTheme.typography.headlineSmall.copy(color = Color.DarkGray),
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        OwnerInfo()
        HorizontalDivider(modifier = Modifier, color = Color.LightGray)
        Text(
            article.content,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun OwnerInfo() {
    Column(
        modifier = Modifier.padding(start = 10.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
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
        DetailScreen(modifier = Modifier.height(100.dp), rememberNavController(), article)
    }
}