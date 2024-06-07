package com.devmike.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devmike.database.baseDb.BaseDbTest
import com.devmike.database.dao.SearchQueryDao
import com.devmike.database.entities.RecentSearchEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant

@RunWith(AndroidJUnit4::class)
class SearchQueryDaoTest : BaseDbTest() {
    private lateinit var searchQueryDao: SearchQueryDao

    @Before
    fun setUp() {
        searchQueryDao = db.searchQueryDao()
    }

    @Test
    fun `insert recent search entity`() {
        val query = "apple"
        val searchEntity = RecentSearchEntity(query, Instant.now())
        runTest {
            val insertedRowId = searchQueryDao.insert(searchEntity)
            assertTrue(insertedRowId > 0L)
            val retrievedQuery = searchQueryDao.getSearchQueryWithCalories(query)
            assertEquals(1, retrievedQuery.size)

            val retrievedSearch = retrievedQuery.find { it.searchQuery.queryString == query }?.searchQuery?.queryString
            assertEquals(query, retrievedSearch)
        }
    }

    @Test
    fun `clear recent search queries`() {
        val query = "pizza"

        runTest {
            searchQueryDao.insert(RecentSearchEntity(query, Instant.now()))
            val initialCount = searchQueryDao.getRecentQueries(Int.MAX_VALUE).first()
            assertTrue(initialCount.isNotEmpty())

            searchQueryDao.clearRecentSearchQueries()
            val clearedCount = searchQueryDao.getRecentQueries(Int.MAX_VALUE).first()
            assertTrue(clearedCount.isEmpty())
        }
    }
}
