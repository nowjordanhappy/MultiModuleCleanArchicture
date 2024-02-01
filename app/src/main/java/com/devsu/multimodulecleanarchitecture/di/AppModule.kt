package com.devsu.multimodulecleanarchitecture.di

import com.devsu.core_ui.model.DataState
import com.devsu.navigation.NavigationManager
import com.devsu.preferences.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.prefs.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager()

}