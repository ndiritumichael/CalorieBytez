package com.devmike.saveditems

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmike.domain.repositories.CaloriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SavedItemsViewmodel
    @Inject
    constructor(private val repository: CaloriesRepository) : ViewModel() {
        val savedCalorieItems =
            repository.getAllCalories().stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                emptyList(),
            )
    }
