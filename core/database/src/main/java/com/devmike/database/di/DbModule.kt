package com.devmike.database.di

import android.content.Context
import androidx.room.Room
import com.devmike.database.CalorieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun providesCalorieDatabase(
        @ApplicationContext context: Context,
    ): CalorieDatabase {
        return Room.databaseBuilder(
            context,
            CalorieDatabase::class.java,
            "calorie_database",
        ).build()
    }
}
