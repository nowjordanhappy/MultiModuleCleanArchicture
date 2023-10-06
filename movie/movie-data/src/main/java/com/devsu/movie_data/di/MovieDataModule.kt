package com.devsu.movie_data.di

import com.devsu.core.Constants
import com.devsu.movie_data.remote.MovieApi
import com.devsu.movie_data.remote.utils.OAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDataModule {

    @Provides
    @Singleton
    fun provideOAuthentication(): OAuthInterceptor {
        return OAuthInterceptor("Bearer", Constants.AUTH_TOKEN)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: OAuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): MovieApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }
}