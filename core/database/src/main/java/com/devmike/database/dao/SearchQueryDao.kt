package com.devmike.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.devmike.database.entities.RecentSearchEntity
import com.devmike.database.entities.SearchQueryWithCalories
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchQueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchQuery: RecentSearchEntity): Long

    @Query("SELECT * FROM recentSearchQueries ORDER BY queriedDate DESC LIMIT :limit")
    fun getRecentQueries(limit: Int): Flow<List<RecentSearchEntity>>

    @Query(value = "DELETE FROM recentSearchQueries")
    fun clearRecentSearchQueries()

    @Transaction
    @Query("SELECT * FROM recentSearchQueries WHERE queryString = :query")
    suspend fun getSearchQueryWithCalories(query: String): List<SearchQueryWithCalories>
}
