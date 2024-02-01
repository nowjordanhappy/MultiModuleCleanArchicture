package com.devsu.streaming_data.di

import com.devsu.core.Constants
import com.devsu.preferences.PreferencesManager
import com.devsu.streaming_data.mapper.RadioDtoMapper
import com.devsu.streaming_data.remote.RadioApi
import com.devsu.streaming_data.repository.RadioRepositoryImpl
import com.devsu.streaming_data.repository.UserRepositoryImpl
import com.devsu.streaming_domain.repository.RadioRepository
import com.devsu.streaming_domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StreamingDataModule {

    @Provides
    @Singleton
    @Named(Constants.DEFAULT_OKHTTP_DEPENDENCY_NAME)
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRadioApi(@Named(Constants.DEFAULT_OKHTTP_DEPENDENCY_NAME) client: OkHttpClient): RadioApi{
        return Retrofit.Builder()
            .baseUrl(Constants.RADIO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        preferencesManager: PreferencesManager
    ): UserRepository {
        return UserRepositoryImpl(
            preferencesManager
        )
    }

    @Provides
    @Singleton
    fun provideRadioRepository(
        api: RadioApi,
    ): RadioRepository{
        return RadioRepositoryImpl(
            service = api,
            mapper = RadioDtoMapper()
        )
    }
}