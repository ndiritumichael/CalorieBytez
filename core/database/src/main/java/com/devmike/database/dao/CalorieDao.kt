package com.devmike.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devmike.database.entities.CalorieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CalorieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calorie: CalorieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(calories: List<CalorieEntity>)

    @Query("SELECT * FROM calorie_table WHERE name = :name")
    suspend fun getCalorieById(name: String): CalorieEntity?

    @Query("Select * from calorie_table")
    fun getAllCalories(): Flow<List<CalorieEntity>>

    @Query("DELETE FROM calorie_table")
    suspend fun deleteAll()
}
