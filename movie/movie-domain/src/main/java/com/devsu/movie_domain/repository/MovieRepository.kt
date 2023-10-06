package com.devsu.movie_domain.repository

import com.devsu.movie_domain.model.Movie

interface MovieRepository {
    suspend fun getNowPlayingMovies(
        language: String,
        page: Int,
    ): List<Movie>
}