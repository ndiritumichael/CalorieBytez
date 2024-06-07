package com.devmike.data

import com.devmike.data.repository.RecentSearchesRepositoryImpl
import com.devmike.database.dao.SearchQueryDao
import com.devmike.database.entities.RecentSearchEntity
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.Instant

@ExperimentalCoroutinesApi
class RecentSearchesRepositoryImplTest {
    private lateinit var repository: RecentSearchesRepositoryImpl
    private val mockSearchQueryDao: SearchQueryDao = mockk()

    @Before
    fun setUp() {
        repository = RecentSearchesRepositoryImpl(mockSearchQueryDao)
    }

    @Test
    fun `getRecentSearches should return recent search queries`() =
        runTest {
            val limit = 5
            val recentSearchEntities =
                listOf(
                    RecentSearchEntity(queryString = "tomato and cabbage", queriedDate = Instant.now()),
                    RecentSearchEntity(queryString = "pineapple on pizza", queriedDate = Instant.now()),
                )
            coEvery { mockSearchQueryDao.getRecentQueries(limit) } returns flowOf(recentSearchEntities)

            val result = repository.getRecentSearches(limit).toList()

            Truth.assertThat(result).hasSize(1)
            Truth.assertThat(result.first()).containsExactly("tomato and cabbage", "pineapple on pizza")
        }

    @Test
    fun `getRecentSearches should return empty list when no recent searches`() =
        runTest {
            val limit = 5
            coEvery { mockSearchQueryDao.getRecentQueries(limit) } returns flowOf(emptyList())

            val result = repository.getRecentSearches(limit).toList()

            Truth.assertThat(result).hasSize(1)
            Truth.assertThat(result.first()).isEmpty()
        }
}
