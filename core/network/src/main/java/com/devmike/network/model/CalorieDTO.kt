package com.devmike.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalorieDTO(
    @SerialName("calories")
    val calories: Double,
    @SerialName("carbohydrates_total_g")
    val carbohydratesTotalG: Double,
    @SerialName("cholesterol_mg")
    val cholesterolMg: Double,
    @SerialName("fat_saturated_g")
    val fatSaturatedG: Double,
    @SerialName("fat_total_g")
    val fatTotalG: Double,
    @SerialName("fiber_g")
    val fiberG: Double,
    @SerialName("name")
    val name: String,
    @SerialName("potassium_mg")
    val potassiumMg: Double,
    @SerialName("protein_g")
    val proteinG: Double,
    @SerialName("serving_size_g")
    val servingSizeG: Double,
    @SerialName("sodium_mg")
    val sodiumMg: Double,
    @SerialName("sugar_g")
    val sugarG: Double,
)

@Serializable
data class CalorieResponse(
    val items: List<CalorieDTO>,
)
