package com.devsu.movie_domain

import com.devsu.movie_domain.model.Movie
import com.devsu.movie_domain.repository.MovieRepository

class FakeMovieRepository: MovieRepository {
    override suspend fun getNowPlayingMovies(language: String, page: Int): List<Movie> {
        return listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                originalTitle = "Original Movie 1",
                overview = "This is the overview for Movie 1.",
                photoUrl = "https://example.com/movie1.jpg",
                posterUrl = "https://example.com/poster1.jpg",
                releaseDate = "2023-10-01",
                voteAverage = 7.5,
                isForAdult = false
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                originalTitle = "Original Movie 2",
                overview = "This is the overview for Movie 2.",
                photoUrl = "https://example.com/movie2.jpg",
                posterUrl = "https://example.com/poster2.jpg",
                releaseDate = "2023-09-15",
                voteAverage = 8.0,
                isForAdult = false
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                originalTitle = "Original Movie 3",
                overview = "This is the overview for Movie 3.",
                photoUrl = "https://example.com/movie3.jpg",
                posterUrl = "https://example.com/poster3.jpg",
                releaseDate = "2023-11-05",
                voteAverage = null,
                isForAdult = true
            )
        )
    }
}