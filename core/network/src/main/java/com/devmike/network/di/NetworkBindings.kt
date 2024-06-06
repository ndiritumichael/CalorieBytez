package com.devmike.network.di

import com.devmike.network.datasource.CalorieNetworkSource
import com.devmike.network.datasource.CalorieNetworkSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBindings {
    @Binds
    abstract fun provideCalorieSource(
        impl: CalorieNetworkSourceImpl,
    ): CalorieNetworkSource
}
