package com.devmike.network.utils

import com.devmike.domain.models.AppErrors
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.utils.io.errors.IOException
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <reified T> safeApiCall(
    apiCall: () -> HttpResponse,
): Result<T> {
    return try {
        val response = apiCall()

        when {
            response.status.isSuccess() -> {
                // Handle successful response
                val data: T = response.body()

                Result.success(data)
            }

            response.status == HttpStatusCode.NotFound -> {
                Result.failure(AppErrors.NotFound())
            }

            response.status == HttpStatusCode.Forbidden -> {
                Result.failure(AppErrors.Unauthorized())
            }

            else -> {
                Result.failure(AppErrors.Unknown())
            }
        }
    } catch (e: SocketTimeoutException) {
        Result.failure(AppErrors.Timeout())
    } catch (e: IOException) {
        Result.failure(AppErrors.NoInternet())
    } catch (e: Exception) {
        if (e is CancellationException) {
            throw e
        } else {
            Result.failure(AppErrors.Unknown())
        }
    }
}
