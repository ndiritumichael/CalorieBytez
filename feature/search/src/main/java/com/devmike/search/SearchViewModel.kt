package com.devmike.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmike.domain.models.AppErrors
import com.devmike.domain.models.CalorieModel
import com.devmike.domain.models.FetchItemState
import com.devmike.domain.repositories.CaloriesRepository
import com.devmike.domain.repositories.RecentSearchesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject
    constructor(
        private val repository: CaloriesRepository,
        private val searchesRepository: RecentSearchesRepository,
    ) : ViewModel() {
        val recentSearchList =
            searchesRepository.getRecentSearches(10)
                .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

        private var searchJob: Job? = null
        var searchQuery: MutableState<String> = mutableStateOf("")
            private set

        private val _caloriesSearchState: MutableStateFlow<FetchItemState<List<CalorieModel>>> =
            MutableStateFlow(FetchItemState.Idle)
        val caloriesSearchState: StateFlow<FetchItemState<List<CalorieModel>>> =
            _caloriesSearchState.asStateFlow()

        fun modifyQuery(query: String) {
            searchQuery.value = query
        }

        fun searchCalories() {
            searchJob?.cancel()

            if (searchQuery.value.length > 3) {
                _caloriesSearchState.value = FetchItemState.Loading
                searchJob =
                    viewModelScope.launch {
                        repository.getCalories(searchQuery.value.trim()).onSuccess { calories ->
                            _caloriesSearchState.value = FetchItemState.Success(calories)
                        }.onFailure { throwable ->

                            if (throwable is AppErrors) {
                                _caloriesSearchState.value = FetchItemState.Error(throwable)
                            }
                        }
                    }
            }
        }
    }
