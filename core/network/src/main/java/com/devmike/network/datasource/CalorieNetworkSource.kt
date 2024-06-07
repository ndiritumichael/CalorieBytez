package com.devmike.network.datasource

import com.devmike.network.model.CalorieResponse

interface CalorieNetworkSource {
    suspend fun getCalorieInformation(query: String): Result<CalorieResponse>
}
