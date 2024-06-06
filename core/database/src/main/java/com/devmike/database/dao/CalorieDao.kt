package com.devmike.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devmike.database.entities.CalorieEntity

@Dao
interface CalorieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calorie: CalorieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(calories: List<CalorieEntity>)

    @Query("SELECT * FROM calorie_table WHERE name = :name")
    suspend fun getCalorieById(name: String): CalorieEntity?
}
