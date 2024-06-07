package com.devmike.network

import com.devmike.network.model.CalorieDTO

internal val mockValidJsonResponse =
    """
    {
      "items": [
        {
          "name": "onion",
          "calories": 126.7,
          "serving_size_g": 283.495,
          "fat_total_g": 0.5,
          "fat_saturated_g": 0.1,
          "protein_g": 3.9,
          "sodium_mg": 8,
          "potassium_mg": 99,
          "cholesterol_mg": 0,
          "carbohydrates_total_g": 28.6,
          "fiber_g": 4,
          "sugar_g": 13.3
        },
        {
          "name": "tomato",
          "calories": 18.2,
          "serving_size_g": 100,
          "fat_total_g": 0.2,
          "fat_saturated_g": 0,
          "protein_g": 0.9,
          "sodium_mg": 4,
          "potassium_mg": 23,
          "cholesterol_mg": 0,
          "carbohydrates_total_g": 3.9,
          "fiber_g": 1.2,
          "sugar_g": 2.6
        },
        {
          "name": "pizza",
          "calories": 262.9,
          "serving_size_g": 100,
          "fat_total_g": 9.8,
          "fat_saturated_g": 4.5,
          "protein_g": 11.4,
          "sodium_mg": 587,
          "potassium_mg": 217,
          "cholesterol_mg": 16,
          "carbohydrates_total_g": 32.9,
          "fiber_g": 2.3,
          "sugar_g": 3.6
        }
      ]
    }
    """.trimIndent()

object SampleDTO {
    val onionDTO =
        CalorieDTO(
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

    val tomatoDTO =
        CalorieDTO(
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

    val pizzaDTO =
        CalorieDTO(
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
