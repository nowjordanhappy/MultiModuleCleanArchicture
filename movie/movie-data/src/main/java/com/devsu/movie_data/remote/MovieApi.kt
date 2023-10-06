package com.devsu.movie_data.remote

import com.devsu.movie_data.remote.dto.NowPlayingMovieListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {
    @Headers("Accept: application/json")
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): NowPlayingMovieListResponse

}