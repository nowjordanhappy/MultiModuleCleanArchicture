package com.devsu.movie_domain.di

import com.devsu.movie_domain.repository.MovieRepository
import com.devsu.movie_domain.use_case.GetNowPlayingMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MovieDomainModule {
    @ViewModelScoped
    @Provides
    fun provideGetNowPlayingMovies(
        repository: MovieRepository
    ): GetNowPlayingMovies{
        return GetNowPlayingMovies(repository)
    }
}