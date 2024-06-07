package com.devmike.data.mappers

import com.devmike.database.entities.CalorieEntity
import com.devmike.domain.models.CalorieModel
import com.devmike.network.model.CalorieDTO

fun CalorieDTO.toCalorieModel(): CalorieModel {
    return CalorieModel(
        calories = this.calories,
        carbohydratesTotalG = this.carbohydratesTotalG,
        cholesterolMg = this.cholesterolMg,
        fatSaturatedG = this.fatSaturatedG,
        fatTotalG = this.fatTotalG,
        fiberG = this.fiberG,
        name = this.name,
        potassiumMg = this.potassiumMg,
        proteinG = this.proteinG,
        servingSizeinGrams = this.servingSizeG,
        sodiumMg = this.sodiumMg,
        sugarG = this.sugarG,
    )
}

fun CalorieModel.toCalorieEntity(): CalorieEntity {
    return CalorieEntity(
        calories = this.calories,
        carbohydratesTotalG = this.carbohydratesTotalG,
        cholesterolMg = this.cholesterolMg,
        fatSaturatedG = this.fatSaturatedG,
        fatTotalG = this.fatTotalG,
        fiberG = this.fiberG,
        name = this.name,
        potassiumMg = this.potassiumMg,
        proteinG = this.proteinG,
        servingSizeG = this.servingSizeinGrams,
        sodiumMg = this.sodiumMg,
        sugarG = this.sugarG,
    )
}

fun CalorieEntity.toCalorieModel(): CalorieModel {
    return CalorieModel(
        calories = this.calories,
        carbohydratesTotalG = this.carbohydratesTotalG,
        cholesterolMg = this.cholesterolMg,
        fatSaturatedG = this.fatSaturatedG,
        fatTotalG = this.fatTotalG,
        fiberG = this.fiberG,
        name = this.name,
        potassiumMg = this.potassiumMg,
        proteinG = this.proteinG,
        servingSizeinGrams = this.servingSizeG,
        sodiumMg = this.sodiumMg,
        sugarG = this.sugarG,
    )
}
