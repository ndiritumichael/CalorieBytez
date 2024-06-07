package com.devmike.network

import com.devmike.domain.models.AppErrors
import com.devmike.network.datasource.CalorieNetworkSourceImpl
import com.google.common.truth.Truth
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CalorieClientApiTest {
    @Test
    fun `getCalorie response code 200 deserializes data correctly `() =
        runTest {
            val mockEngine =
                MockEngine {
                    respond(
                        mockValidJsonResponse,
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }

            val httpClient =
                HttpClient(mockEngine) {
                    install(ContentNegotiation) {
                        json()
                    }
                    install(DefaultRequest) {
                        header(HttpHeaders.ContentType, ContentType.Application.Json)
                    }
                }

            val netWorkRepo = CalorieNetworkSourceImpl(httpClient)
            val result = netWorkRepo.getCalorieInformation("pizza")

            Truth.assertThat(result.getOrNull()?.items).containsAnyOf(SampleDTO.tomatoDTO, SampleDTO.onionDTO)
        }

    @Test
    fun `get client returns failure for errors`() =
        runTest {
            val mockEngine =
                MockEngine {
                    respond(
                        """
                        invalid data
                        """.trimIndent(),
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        status = HttpStatusCode.BadRequest,
                    )
                }

            val httpClient =
                HttpClient(mockEngine) {
                    install(ContentNegotiation) {
                        json()
                    }
                    install(DefaultRequest) {
                        header(HttpHeaders.ContentType, ContentType.Application.Json)
                    }
                }

            val netWorkRepo = CalorieNetworkSourceImpl(httpClient)
            val result = netWorkRepo.getCalorieInformation("pizza")

            Truth.assertThat(result.getOrNull()).isNull()
        }

    @Test
    fun ` given a response 404 the client returns AppErrors not found`() =
        runTest {
            val mockEngine =
                MockEngine {
                    respond(
                        """
                        invalid data
                        """.trimIndent(),
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        status = HttpStatusCode.NotFound,
                    )
                }

            val httpClient =
                HttpClient(mockEngine) {
                    install(ContentNegotiation) {
                        json()
                    }
                    install(DefaultRequest) {
                        header(HttpHeaders.ContentType, ContentType.Application.Json)
                    }
                }

            val netWorkRepo = CalorieNetworkSourceImpl(httpClient)
            val result = netWorkRepo.getCalorieInformation("pizza")
            Truth.assertThat(result.exceptionOrNull()).isInstanceOf(AppErrors.NotFound::class.java)
        }
}
