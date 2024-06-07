package com.devmike.domain.repositories

import kotlinx.coroutines.flow.Flow

interface RecentSearchesRepository {
    fun getRecentSearches(limit: Int): Flow<List<String>>
}
