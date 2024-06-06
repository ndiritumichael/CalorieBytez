package com.devmike.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devmike.database.covertor.InstantConverter
import com.devmike.database.dao.CalorieDao
import com.devmike.database.dao.CrossRefDao
import com.devmike.database.dao.SearchQueryDao
import com.devmike.database.entities.CalorieEntity
import com.devmike.database.entities.RecentSearchEntity
import com.devmike.database.entities.SearchQueryCalorieCrossRef

@Database(
    entities = [
        CalorieEntity::class,
        RecentSearchEntity::class,
        SearchQueryCalorieCrossRef::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
abstract class CalorieDatabase : RoomDatabase() {
    abstract fun calorieDao(): CalorieDao

    abstract fun searchQueryDao(): SearchQueryDao

    abstract fun crossRefDao(): CrossRefDao
}
