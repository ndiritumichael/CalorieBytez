package com.devmike.data.repository

import com.devmike.data.mappers.toCalorieEntity
import com.devmike.data.mappers.toCalorieModel
import com.devmike.database.dao.CalorieDao
import com.devmike.database.dao.CrossRefDao
import com.devmike.database.dao.SearchQueryDao
import com.devmike.database.entities.RecentSearchEntity
import com.devmike.database.entities.SearchQueryCalorieCrossRef
import com.devmike.domain.models.AppErrors
import com.devmike.domain.models.CalorieModel
import com.devmike.domain.repositories.CaloriesRepository
import com.devmike.network.datasource.CalorieNetworkSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import javax.inject.Inject

class CaloriesRepositoryImpl
    @Inject
    constructor(
        private val networkSource: CalorieNetworkSource,
        private val calorieDao: CalorieDao,
        private val searchQueryDao: SearchQueryDao,
        private val crossRefDao: CrossRefDao,
    ) : CaloriesRepository {
        override suspend fun getCalories(query: String): Result<List<CalorieModel>> {
            val cachedQuery = searchQueryDao.getSearchQueryWithCalories(query)
            if (cachedQuery.isNotEmpty()) {
                return Result.success(cachedQuery.first().calories.map { it.toCalorieModel() })
            } else {
                val networkResult = networkSource.getCalorieInformation(query)

                networkResult.onSuccess {
                    if (it.items.isEmpty()) {
                        return Result.failure(AppErrors.Empty())
                    }

                    saveNetworkCalorieDb(
                        query,
                        it.items.map { calorieResponse ->
                            calorieResponse.toCalorieModel()
                        },
                    )
                }.onFailure {
                    return Result.failure(it)
                }
            }
            val offlineResult =
                searchQueryDao.getSearchQueryWithCalories(query).first().calories.map {
                    it.toCalorieModel()
                }

            return Result.success(offlineResult)
        }

        override suspend fun getFoodItem(name: String): CalorieModel? {
            return calorieDao.getCalorieById(name)?.toCalorieModel()
        }

    override fun getAllCalories(): Flow<List<CalorieModel>> = calorieDao.getAllCalories().map { calorieEntities ->
        calorieEntities.map { it.toCalorieModel() }
    }

    private suspend fun saveNetworkCalorieDb(
            query: String,
            calories: List<CalorieModel>,
        ) {
            searchQueryDao.insert(
                RecentSearchEntity(
                    query,
                    Instant.now(),
                ),
            )

            calorieDao.insertAll(
                calories.map {
                    it.toCalorieEntity()
                },
            )

            calories.forEach {
                crossRefDao.insert(
                    SearchQueryCalorieCrossRef(
                        query,
                        it.name,
                    ),
                )
            }
        }
    }
