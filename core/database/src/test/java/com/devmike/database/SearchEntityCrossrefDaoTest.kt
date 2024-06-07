package com.devmike.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devmike.database.baseDb.BaseDbTest
import com.devmike.database.dao.CalorieDao
import com.devmike.database.dao.CrossRefDao
import com.devmike.database.dao.SearchQueryDao
import com.devmike.database.entities.RecentSearchEntity
import com.devmike.database.entities.SearchQueryCalorieCrossRef
import com.devmike.database.util.SampleData.onion
import com.devmike.database.util.SampleData.tomato
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant

@RunWith(AndroidJUnit4::class)
class SearchEntityCrossrefDaoTest : BaseDbTest() {
    private lateinit var calorieDao: CalorieDao
    private lateinit var crossRefDao: CrossRefDao
    private lateinit var searchQueryDao: SearchQueryDao

    private val searchQuery = "Onion with Tomatoes"

    @Before
    fun setUp() {
        calorieDao = db.calorieDao()
        searchQueryDao = db.searchQueryDao()
        crossRefDao = db.crossRefDao()
    }

    @Test
    fun `insertCrossRef works`() =
        runTest {
            calorieDao.insertAll(listOf(onion, tomato))
            crossRefDao.insert(SearchQueryCalorieCrossRef(searchQuery, onion.name))
            crossRefDao.insert(SearchQueryCalorieCrossRef(searchQuery, tomato.name))
            searchQueryDao.insert(RecentSearchEntity(searchQuery, Instant.now()))

            val searchQueryWithCalories = db.searchQueryDao().getSearchQueryWithCalories(searchQuery)
            val searchResults = searchQueryWithCalories.map { it.calories }.flatten()

            Truth.assertThat(searchResults).containsAnyOf(onion, tomato)
        }

    @Test
    fun `deleteBySearchId works`() =
        runTest {
            calorieDao.insertAll(listOf(onion, tomato))
            crossRefDao.insert(SearchQueryCalorieCrossRef(searchQuery, onion.name))
            crossRefDao.insert(SearchQueryCalorieCrossRef(searchQuery, tomato.name))

            crossRefDao.deleteBySearchId(searchQuery)

            val searchQueryWithCalories = db.searchQueryDao().getSearchQueryWithCalories(searchQuery)

            Truth.assertThat(searchQueryWithCalories).isEmpty()
        }

    @Test
    fun `deleteByCaloriename works`() =
        runTest {
            calorieDao.insertAll(listOf(onion, tomato))
            crossRefDao.insert(SearchQueryCalorieCrossRef(searchQuery, onion.name))
            crossRefDao.insert(SearchQueryCalorieCrossRef(searchQuery, tomato.name))

            crossRefDao.deleteByCalorieId(onion.name)

            val searchQueryWithCalories = db.searchQueryDao().getSearchQueryWithCalories(searchQuery)

            Truth.assertThat(searchQueryWithCalories).doesNotContain(tomato)
        }

    @Test
    fun ` deleteAll works`() =
        runTest {
            calorieDao.insertAll(listOf(onion, tomato))
            crossRefDao.insert(SearchQueryCalorieCrossRef(searchQuery, onion.name))
            crossRefDao.insert(SearchQueryCalorieCrossRef(searchQuery, tomato.name))

            crossRefDao.deleteAll()

            val searchQueryWithCalories = db.searchQueryDao().getSearchQueryWithCalories(searchQuery)

            Truth.assertThat(searchQueryWithCalories).isEmpty()
        }
}
