package com.devsu.core_ui.di

import android.app.Application
import com.devsu.core_ui.utils.ManagerConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreUiModule {
    @Provides
    @Singleton
    fun provideManagerConnection(application: Application): ManagerConnection {
        return ManagerConnection(application.applicationContext)
    }
}