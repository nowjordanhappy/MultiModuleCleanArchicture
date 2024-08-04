package com.devsu.multimodulecleanarchitecture.di

import com.devsu.core.Constants
import com.devsu.multimodulecleanarchitecture.BuildConfig
import com.devsu.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager()

    @Singleton
    @Provides
    @Named(Constants.DEFAULT_MOVIEDB_API_KEY_NAME)
    fun providesMovieDBApiKey() = BuildConfig.MOVIEDB_API_KEY

    @Singleton
    @Provides
    @Named(Constants.DEFAULT_YOUTUBE_API_KEY_NAME)
    fun providesYouTubeApiKey() = BuildConfig.YOUTUBE_API_KEY
}