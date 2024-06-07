package com.devmike.testing.fake

import com.devmike.domain.models.AppErrors
import com.devmike.domain.models.CalorieModel
import com.devmike.domain.repositories.CaloriesRepository
import com.devmike.testing.sampleData.SampleModels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCaloriesRepository : CaloriesRepository {
    private val calorieMap = mutableMapOf<String, List<CalorieModel>>()
    private val foodItemMap = mutableMapOf<String, CalorieModel>()

    private var returnError = false

    fun setReturnError(value: Boolean) {
        returnError = value
    }

    fun setCalories(
        query: String,
        calories: List<CalorieModel>,
    ) {
        calorieMap[query] = calories
    }

    fun setFoodItem(
        name: String,
        calorieModel: CalorieModel,
    ) {
        foodItemMap[name] = calorieModel
    }

    override suspend fun getCalories(query: String): Result<List<CalorieModel>> {
        if (returnError) return Result.failure(AppErrors.Unknown())
        return calorieMap[query]?.let { Result.success(it) }
            ?: Result.failure(Exception("No data found for query: $query"))
    }

    override suspend fun getFoodItem(name: String): CalorieModel? {
        return foodItemMap[name]
    }

    override fun getAllCalories(): Flow<List<CalorieModel>> {
        return flowOf(
            listOf(
                SampleModels.tomatomodel,
                SampleModels.pizzamodel,
            ),
        )
    }
}
