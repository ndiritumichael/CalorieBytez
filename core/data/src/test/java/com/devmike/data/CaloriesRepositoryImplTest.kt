package com.devmike.data

import com.devmike.data.mappers.toCalorieModel
import com.devmike.data.repository.CaloriesRepositoryImpl
import com.devmike.database.dao.CalorieDao
import com.devmike.database.dao.CrossRefDao
import com.devmike.database.dao.SearchQueryDao
import com.devmike.database.entities.SearchQueryWithCalories
import com.devmike.domain.models.AppErrors
import com.devmike.network.datasource.CalorieNetworkSource
import com.devmike.network.model.CalorieResponse
import com.devmike.sampleData.SampleModels
import com.devmike.testing.sampleData.SampleDto
import com.devmike.testing.sampleData.SampleEntities
import com.devmike.testing.sampleData.SampleSearchEntity
import com.devmike.testing.sampleData.SampleSearchWithCalorie
import com.google.common.truth.Truth
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CaloriesRepositoryImplTest {
    private lateinit var repository: CaloriesRepositoryImpl
    private val mockNetworkSource: CalorieNetworkSource = mockk()
    private val mockCalorieDao: CalorieDao = mockk()
    private val mockSearchQueryDao: SearchQueryDao = mockk()
    private val mockCrossRefDao: CrossRefDao = mockk()

    @Before
    fun setup() {
        repository =
            CaloriesRepositoryImpl(
                mockNetworkSource,
                mockCalorieDao,
                mockSearchQueryDao,
                mockCrossRefDao,
            )
    }

     @Test
    fun` getCalories cachedQueryExists returnsSuccessWithCachedData`() =
        runTest {
            val query = SampleSearchEntity.tomatowithpizza.queryString
            val cachedCalories =
                listOf(
                    SampleEntities.tomato,
                    SampleEntities.onion,
                )
            val cachedSearchQuery =
                SearchQueryWithCalories(
                    searchQuery = SampleSearchEntity.tomatowithpizza,
                    calories = cachedCalories,
                )

            coEvery { mockSearchQueryDao.getSearchQueryWithCalories(query) } returns listOf(cachedSearchQuery)

            mockSearchQueryDao.getSearchQueryWithCalories(query)

            coVerify {
                mockNetworkSource wasNot called
            }
            coVerify {
                mockSearchQueryDao.getSearchQueryWithCalories(query)
            }

            val result = repository.getCalories(query)

            Truth.assertThat(result.isSuccess).isTrue()

            Truth.assertThat(result.getOrNull()).containsAnyIn(listOf(SampleModels.tomatomodel))
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getCalories networkRequestSuccessful returns with data`() =
        runTest {
            val query = SampleSearchEntity.pizzaonion.queryString
            val networkCalories =
                listOf(
                    SampleDto.onionDTO,
                    SampleDto.pizzaDTO,
                )

            coEvery {
                mockNetworkSource.getCalorieInformation(query)
            } returns Result.success(CalorieResponse(networkCalories))

            coEvery {
                mockCalorieDao.insertAll(any())
            }

            coEvery {
                mockSearchQueryDao.getSearchQueryWithCalories(query)
            } returns listOf(SampleSearchWithCalorie.pizzaonion)
            coEvery {
                mockSearchQueryDao.insert(SampleSearchEntity.pizzaonion)
            } returns 9999L

            coEvery {
                mockCrossRefDao.insert(any())
            }

            val result = repository.getCalories(query)

            Truth.assertThat(result.getOrNull()).containsAnyIn(networkCalories.map { it.toCalorieModel() })
        }

    @Test
    fun ` getCalories networkRequestFailed returnsFailure`() =
        runTest {
            val query = "Invalid query"
            val error = AppErrors.Unauthorized()

            coEvery { mockNetworkSource.getCalorieInformation(query) } returns Result.failure(error)
            coEvery { mockSearchQueryDao.getSearchQueryWithCalories(query) } returns emptyList()

            val result = repository.getCalories(query)

            Truth.assertThat(result.isFailure).isTrue()
            Truth.assertThat(result.exceptionOrNull()).isInstanceOf(AppErrors.Unauthorized::class.java)
        }
}
