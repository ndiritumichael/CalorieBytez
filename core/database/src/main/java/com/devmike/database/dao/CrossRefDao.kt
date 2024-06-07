package com.devmike.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devmike.database.entities.SearchQueryCalorieCrossRef

@Dao
interface CrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crossRef: SearchQueryCalorieCrossRef)

    @Query("DELETE FROM search_query_calorie_cross_ref WHERE queryString = :searchId")
    suspend fun deleteBySearchId(searchId: String)

    @Query("DELETE FROM search_query_calorie_cross_ref WHERE name = :caloriename")
    suspend fun deleteByCalorieId(caloriename: String)

    @Query("DELETE FROM search_query_calorie_cross_ref")
    suspend fun deleteAll()
}
