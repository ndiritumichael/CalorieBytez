package com.devmike.data.repository

import com.devmike.domain.models.CalorieModel

interface CaloriesRepository {
    suspend fun getCalories(query: String): Result<List<CalorieModel>>

    suspend fun getFoodItem(name: String): CalorieModel?
}
