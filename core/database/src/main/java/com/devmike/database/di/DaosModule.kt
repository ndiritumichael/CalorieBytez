package com.devmike.database.di

import com.devmike.database.CalorieDatabase
import com.devmike.database.dao.CalorieDao
import com.devmike.database.dao.CrossRefDao
import com.devmike.database.dao.SearchQueryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesCaloriesDAo(
        database: CalorieDatabase,
    ): CalorieDao = database.calorieDao()

    @Provides
    fun providesSearchEntityDao(
        database: CalorieDatabase,
    ): SearchQueryDao = database.searchQueryDao()

    @Provides
    fun providesSearchEntityRefDao(database: CalorieDatabase): CrossRefDao = database.crossRefDao()
}
