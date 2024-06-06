package com.devmike.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calorie_table")
data class CalorieEntity(
    val calories: Double,
    val carbohydratesTotalG: Double,
    val cholesterolMg: Double,
    val fatSaturatedG: Double,
    val fatTotalG: Double,
    val fiberG: Double,
    @PrimaryKey
    val name: String,
    val potassiumMg: Double,
    val proteinG: Double,
    val servingSizeG: Double,
    val sodiumMg: Double,
    val sugarG: Double,
)
