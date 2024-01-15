package com.devsu.streaming_data.di

import com.devsu.streaming_data.repository.UserRepositoryImpl
import com.devsu.streaming_domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StreamingDataModule {
    @Provides
    @Singleton
    fun provideUserRepository(
    ): UserRepository {
        return UserRepositoryImpl(
        )
    }
}