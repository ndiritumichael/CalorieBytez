package com.devmike.testing.fake

import com.devmike.domain.repositories.RecentSearchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRecentSearchesRepository : RecentSearchesRepository {
    private val recentSearchesFlow = MutableStateFlow(listOf<String>())

    fun setRecentSearches(searches: List<String>) {
        recentSearchesFlow.value = searches
    }

    override fun getRecentSearches(limit: Int): Flow<List<String>> {
        return recentSearchesFlow
    }
}
