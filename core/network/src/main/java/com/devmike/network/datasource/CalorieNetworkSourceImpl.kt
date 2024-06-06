package com.devmike.network.datasource

import com.devmike.network.model.CalorieResponse
import com.devmike.network.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import timber.log.Timber
import javax.inject.Inject

class CalorieNetworkSourceImpl
    @Inject
    constructor(private val client: HttpClient) : CalorieNetworkSource {
        override suspend fun getCalorieInformation(query: String): Result<CalorieResponse> {
            Timber.log(5, "calling api $query")
            return safeApiCall {
                client.get("nutrition") {
                    parameter("query", query)
                }
            }
        }
    }
