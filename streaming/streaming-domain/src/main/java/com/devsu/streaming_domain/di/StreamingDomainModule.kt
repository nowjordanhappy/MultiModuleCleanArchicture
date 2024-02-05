package com.devsu.streaming_domain.di

import com.devsu.streaming_domain.repository.RadioRepository
import com.devsu.streaming_domain.repository.UserRepository
import com.devsu.streaming_domain.use_case.GetCurrentUser
import com.devsu.streaming_domain.use_case.GetPopularCountries
import com.devsu.streaming_domain.use_case.GetPopularTags
import com.devsu.streaming_domain.use_case.SearchRadioList
import com.devsu.streaming_domain.use_case.GetUsers
import com.devsu.streaming_domain.use_case.SetCurrentUser
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

    @ViewModelScoped
    @Provides
    fun provideSetCurrentUser(
        repository: UserRepository
    ): SetCurrentUser {
        return SetCurrentUser(
            repository
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetCurrentUser(
        repository: UserRepository
    ): GetCurrentUser{
        return GetCurrentUser(
            repository
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetRadioListByTag(
        repository: RadioRepository
    ): SearchRadioList{
        return SearchRadioList(
            repository
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetPopularTags(
    ): GetPopularTags{
        return GetPopularTags()
    }

    @ViewModelScoped
    @Provides
    fun provideGetPopularCountries(
    ): GetPopularCountries{
        return GetPopularCountries()
    }
}