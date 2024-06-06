package com.devmike.data.di

import com.devmike.data.repository.CaloriesRepository
import com.devmike.data.repository.CaloriesRepositoryImpl
import com.devmike.data.repository.RecentSearchesRepository
import com.devmike.data.repository.RecentSearchesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindRepository(repository: CaloriesRepositoryImpl): CaloriesRepository

    @Binds
    abstract fun bindSearchRepo(repo: RecentSearchesRepositoryImpl): RecentSearchesRepository
}
