package com.devmike.domain.repositories

import com.devmike.domain.models.CalorieModel
import kotlinx.coroutines.flow.Flow

interface CaloriesRepository {
    suspend fun getCalories(query: String): Result<List<CalorieModel>>

    suspend fun getFoodItem(name: String): CalorieModel?

    fun getAllCalories(): Flow<List<CalorieModel>>
}
