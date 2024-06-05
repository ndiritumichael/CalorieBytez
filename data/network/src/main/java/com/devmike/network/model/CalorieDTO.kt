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
    val cholesterolMg: Int,
    @SerialName("fat_saturated_g")
    val fatSaturatedG: Int,
    @SerialName("fat_total_g")
    val fatTotalG: Double,
    @SerialName("fiber_g")
    val fiberG: Double,
    @SerialName("name")
    val name: String,
    @SerialName("potassium_mg")
    val potassiumMg: Int,
    @SerialName("protein_g")
    val proteinG: Double,
    @SerialName("serving_size_g")
    val servingSizeG: Int,
    @SerialName("sodium_mg")
    val sodiumMg: Int,
    @SerialName("sugar_g")
    val sugarG: Double,
)
