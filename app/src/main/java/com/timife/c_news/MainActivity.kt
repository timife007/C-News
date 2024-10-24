package com.timife.c_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.timife.c_news.domain.model.Article
import com.timife.c_news.presentation.navigation.CustomNavType
import com.timife.c_news.presentation.navigation.NewsDetailRoute
import com.timife.c_news.presentation.navigation.NewsListRoute
import com.timife.c_news.presentation.screens.DetailScreen
import com.timife.c_news.presentation.screens.HomeScreen
import com.timife.c_news.ui.theme.CNewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()
            val navController: NavHostController = rememberNavController()
            CNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
                    SnackbarHost(snackbarHostState)
                }) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = NewsListRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<NewsListRoute> {
                            HomeScreen(modifier = Modifier, onArticleClicked = {
                                navController.navigate(NewsDetailRoute(it))
                            }){
                                LaunchedEffect(it) {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(it)
                                    }
                                }
                            }
                        }
                        composable<NewsDetailRoute>(
                            typeMap = mapOf(
                                typeOf<Article>() to CustomNavType.ArticleType,
                            )
                        ) {
                            val arguments = it.toRoute<NewsDetailRoute>()
                            DetailScreen(
                                article = arguments.article,
                            ){
                                navController.navigateUp()
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CNewsTheme {
        Greeting("Android")
    }
}