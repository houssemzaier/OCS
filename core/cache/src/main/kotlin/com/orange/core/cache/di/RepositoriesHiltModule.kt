package com.orange.core.cache.di

import com.orange.core.cache.reporitories.VideoTimestampRepositoryImpl
import com.orange.core.domain.repositories.VideoTimestampRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesHiltModule {
    @Binds
    internal abstract fun bindsVideoTimestampRepository(impl: VideoTimestampRepositoryImpl): VideoTimestampRepository
}
