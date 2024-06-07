package com.devmike.database.util

import com.devmike.database.entities.CalorieEntity
import com.devmike.database.entities.RecentSearchEntity
import java.time.Instant

object SampleData {
    val onion =
        CalorieEntity(
            calories = 126.7,
            carbohydratesTotalG = 28.6,
            cholesterolMg = 0.0,
            fatSaturatedG = 0.1,
            fatTotalG = 0.5,
            fiberG = 4.0,
            name = "onion",
            potassiumMg = 99.0,
            proteinG = 3.9,
            servingSizeG = 283.0,
            sodiumMg = 8.0,
            sugarG = 13.3,
        )

    val tomato =
        CalorieEntity(
            calories = 18.2,
            carbohydratesTotalG = 3.9,
            cholesterolMg = 0.0,
            fatSaturatedG = 0.0,
            fatTotalG = 0.2,
            fiberG = 1.2,
            name = "tomato",
            potassiumMg = 23.0,
            proteinG = 0.9,
            servingSizeG = 100.0,
            sodiumMg = 4.0,
            sugarG = 2.6,
        )

    val pizza =
        CalorieEntity(
            calories = 262.9,
            carbohydratesTotalG = 32.9,
            cholesterolMg = 16.0,
            fatSaturatedG = 4.5,
            fatTotalG = 9.8,
            fiberG = 2.3,
            name = "pizza",
            potassiumMg = 217.0,
            proteinG = 11.4,
            servingSizeG = 100.0,
            sodiumMg = 587.0,
            sugarG = 3.6,
        )
}

object SampleSearchEntity {
    val fruits = RecentSearchEntity("Fruits", Instant.now())
    val vegetables = RecentSearchEntity("Vegetables", Instant.now())
    val tomato = RecentSearchEntity("Tomato", Instant.now())
    val pizza = RecentSearchEntity("Pizza", Instant.now())
    val onion = RecentSearchEntity("Onion", Instant.now())
}
