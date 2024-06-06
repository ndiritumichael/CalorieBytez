package com.devmike.data.repository

import kotlinx.coroutines.flow.Flow

interface RecentSearchesRepository {
    fun getRecentSearches(limit: Int): Flow<List<String>>
}
