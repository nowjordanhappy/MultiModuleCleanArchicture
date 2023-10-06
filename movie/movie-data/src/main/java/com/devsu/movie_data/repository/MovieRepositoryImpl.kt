package com.devsu.movie_data.repository

import com.devsu.movie_data.mapper.MovieDtoMapper
import com.devsu.movie_data.remote.MovieApi
import com.devsu.movie_domain.model.Movie
import com.devsu.movie_domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val service: MovieApi,
    private val mapper: MovieDtoMapper,
) : MovieRepository {
    override suspend fun getNowPlayingMovies(language: String, page: Int): List<Movie> {
        val response = service.getNowPlayingMovies(
            language = language,
            page = page
        )
        val movies = response.results ?: emptyList()

        return movies.mapNotNull { mapper.mapToDomainModel(it) }
    }
}