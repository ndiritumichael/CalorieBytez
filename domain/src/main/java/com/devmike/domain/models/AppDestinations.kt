package com.devmike.domain.models

import kotlinx.serialization.Serializable

sealed interface AppDestinations {

    @Serializable
    data object SearchScreen : AppDestinations

    @Serializable
    data class FoodDetails(val name: String)

    @Serializable
    data object SavedFood : AppDestinations
}
