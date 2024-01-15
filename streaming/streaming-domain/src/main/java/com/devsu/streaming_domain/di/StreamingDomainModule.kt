package com.devsu.streaming_domain.di

import com.devsu.streaming_domain.repository.UserRepository
import com.devsu.streaming_domain.use_case.GetUsers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object StreamingDomainModule {
    @ViewModelScoped
    @Provides
    fun provideGetUsers(
        repository: UserRepository
    ): GetUsers {
        return GetUsers(repository)
    }
}