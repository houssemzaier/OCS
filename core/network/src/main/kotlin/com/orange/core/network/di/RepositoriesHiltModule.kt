package com.orange.core.network.di

import com.orange.core.domain.repositories.ProgramRepository
import com.orange.core.network.repositories.ProgramRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesHiltModule {
    @Binds
    internal abstract fun bindsProgramRepository(impl: ProgramRepositoryImpl): ProgramRepository
}
