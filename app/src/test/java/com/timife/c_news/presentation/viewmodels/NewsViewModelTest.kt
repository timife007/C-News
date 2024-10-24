package com.timife.c_news.presentation.viewmodels

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.c_news.data.TestData
import com.timife.c_news.domain.repositories.NewsRepo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsRepo: NewsRepo

    // Provide a TestDispatcher to manage the coroutine environment
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)


        newsRepo = mockk(relaxed = true)

        // my repository’s getPaginatedArticles function mock to return initial flow of empty data.
        coEvery { newsRepo.getPaginatedArticles(any()) } returns flowOf(PagingData.empty())

        viewModel = NewsViewModel(newsRepo)
    }

    @After
    fun tearDown() {
        //
        Dispatchers.resetMain()
    }

    @Test
    fun `verify that initial state has empty search query`() = runTest {
        assertThat(viewModel.searchQuery.value).isEqualTo("")
    }

    @Test
    fun `verify that onSearchQueryChanged updates search query state`() = runTest {
        val newQuery = "Yoga"
        viewModel.onSearchQueryChanged(newQuery)

        assertThat(viewModel.searchQuery.value).isEqualTo(newQuery)
    }

    @Test
    fun `verify that news flow emits paging data when search query changes`() = runTest {

        val testPagingData = PagingData.from(listOf(TestData.vmQueryResult))
        // mocking repository to return non-empty PagingData flow
        coEvery { newsRepo.getPaginatedArticles("Yoga") } returns flowOf(testPagingData)

        // changing the search query to trigger the news flow
        viewModel.onSearchQueryChanged("Yoga")

        // time to allow debounce emit flow
        advanceTimeBy(300)

        viewModel.news.test {
            val item = awaitItem()
            assertThat(item).isNotNull()
            assertThat(item).isInstanceOf(PagingData::class.java)
        }
    }
}
