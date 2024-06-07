package com.devmike.data.repository

import com.devmike.database.dao.SearchQueryDao
import com.devmike.domain.repositories.RecentSearchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentSearchesRepositoryImpl
    @Inject
    constructor(
        private val searchQueryDao: SearchQueryDao,
    ) : RecentSearchesRepository {
        override fun getRecentSearches(limit: Int): Flow<List<String>> {
            return searchQueryDao.getRecentQueries(limit = limit).map {
                it.map { recentSearchEntity ->
                    recentSearchEntity.queryString
                }
            }
        }
    }
