package com.devmike.fooddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.devmike.domain.models.AppDestinations
import com.devmike.domain.models.CalorieModel
import com.devmike.domain.repositories.CaloriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val caloriesRepository: CaloriesRepository,
    ) : ViewModel() {
        private val foodName: AppDestinations.FoodDetails = savedStateHandle.toRoute()

        private val _foodDetails = MutableStateFlow<CalorieModel?>(null)
        val foodDetails = _foodDetails.asStateFlow()

        init {

            getFoodDetails()
        }

        fun getFoodDetails() {
            viewModelScope.launch {
                val details = caloriesRepository.getFoodItem(foodName.name)
                _foodDetails.value = details
            }
        }
    }
