package com.devmike.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devmike.domain.models.AppErrors
import com.devmike.domain.models.FetchItemState
import com.devmike.testing.fake.FakeCaloriesRepository
import com.devmike.testing.fake.FakeRecentSearchesRepository
import com.devmike.testing.helpers.MainCoroutineRule
import com.devmike.testing.sampleData.SampleModels
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewmodelTest {
    private lateinit var fakeRecentSearchesRepository: FakeRecentSearchesRepository

    private lateinit var fakeCaloriesRepository: FakeCaloriesRepository

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutor: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        fakeRecentSearchesRepository = FakeRecentSearchesRepository()
        fakeCaloriesRepository = FakeCaloriesRepository()
        viewModel = SearchViewModel(fakeCaloriesRepository, fakeRecentSearchesRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getRecentSearches initializes with recent searches`() =
        runTest {
            val recentSearches = listOf("apple", "pizza")
            fakeRecentSearchesRepository.setRecentSearches(recentSearches)

            val result = viewModel.recentSearchList.first()

            Truth.assertThat(result).containsExactly(*recentSearches.toTypedArray())
        }

    @Test
    fun `test modifyQuery updates searchQuery`() {
        viewModel.modifyQuery("onion")

        Truth.assertThat(viewModel.searchQuery.value).isEqualTo("onion")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test searchCalories sets loading state and fetches calories successfully`() =
        runTest {
            val sampleCalories = listOf(SampleModels.tomatomodel)
            fakeCaloriesRepository.setCalories("tomato", sampleCalories)

            viewModel.modifyQuery("tomato")
            viewModel.searchCalories()

            advanceUntilIdle()

            Truth.assertThat(viewModel.caloriesSearchState.value).isInstanceOf(FetchItemState.Success::class.java)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test searchCalories handles error state`() =
        runTest {
            fakeCaloriesRepository.setReturnError(true)

            viewModel.modifyQuery("apple")
            viewModel.searchCalories()

            Truth.assertThat(viewModel.caloriesSearchState.value).isInstanceOf(FetchItemState.Error::class.java)

            val isErr = (viewModel.caloriesSearchState.value as FetchItemState.Error)

            Truth.assertThat(isErr.err).isInstanceOf(AppErrors.Unknown::class.java)
        }

    @Test
    fun `test searchCalories does not search for short queries`() =
        runTest {
            viewModel.modifyQuery("app")
            viewModel.searchCalories()

            Truth.assertThat(viewModel.caloriesSearchState.value).isEqualTo(FetchItemState.Idle)
        }
}
