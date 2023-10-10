package com.devsu.movie_data.mapper

import com.devsu.core.Constants
import com.devsu.movie_data.remote.dto.MovieDto
import com.devsu.movie_data.remote.dto.OriginalLanguage
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class MovieDtoMapperTest {
    private val mapper = MovieDtoMapper()

    @Test
    fun mapToDomainModel_resultsValidMovieObject() {
        val movieDto = MovieDto(
            adult = false,
            backdropPath = "/path/to/backdrop.jpg",
            genreIDS = listOf(28, 12, 14),
            id = 123456789,
            originalLanguage = OriginalLanguage.En,
            originalTitle = "The Shawshank Redemption",
            overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption in the harshness of the prison environment.",
            popularity = 89.987,
            posterPath = "/path/to/poster.jpg",
            releaseDate = "1994-09-22",
            title = "The Shawshank Redemption",
            video = false,
            voteAverage = 9.0,
            voteCount = 234567
        )

        val result = mapper.mapToDomainModel(movieDto)

        assertEquals(123456789, result?.id)
        assertEquals("The Shawshank Redemption", result?.title)
        assertEquals("The Shawshank Redemption", result?.originalTitle)
        assertEquals(
            "Two imprisoned men bond over a number of years, finding solace and eventual redemption in the harshness of the prison environment.",
            result?.overview
        )
        assertContains(Constants.IMAGE_BASE_URL+"/path/to/backdrop.jpg", result?.photoUrl ?: "")
        assertContains(Constants.IMAGE_BASE_URL+"/path/to/poster.jpg", result?.posterUrl ?: "")
        assertEquals("1994-09-22", result?.releaseDate)
        assertEquals(9.0, result?.voteAverage)
        assertEquals(false, result?.isForAdult)
    }

    @Test
    fun mapToDomainModelWithNullImage_resultsValidMovieObjectWithBlankImage() {
        val movieDto = MovieDto(
            adult = false,
            backdropPath = null,
            genreIDS = listOf(28, 12, 14),
            id = 123456789,
            originalLanguage = OriginalLanguage.En,
            originalTitle = "The Shawshank Redemption",
            overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption in the harshness of the prison environment.",
            popularity = 89.987,
            posterPath = null,
            releaseDate = "1994-09-22",
            title = "The Shawshank Redemption",
            video = false,
            voteAverage = 9.0,
            voteCount = 234567
        )

        val result = mapper.mapToDomainModel(movieDto)

        assertEquals(123456789, result?.id)
        assertContains("", result?.photoUrl ?: "")
        assertContains("", result?.posterUrl ?: "")
    }

    @Test
    fun mapToDomainModelWithNullNullId_resultsNullObject() {
        val movieDto = MovieDto(
            adult = false,
            backdropPath = null,
            genreIDS = listOf(28, 12, 14),
            id = null,
            originalLanguage = OriginalLanguage.En,
            originalTitle = "The Shawshank Redemption",
            overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption in the harshness of the prison environment.",
            popularity = 89.987,
            posterPath = null,
            releaseDate = "1994-09-22",
            title = "The Shawshank Redemption",
            video = false,
            voteAverage = 9.0,
            voteCount = 234567
        )

        val result = mapper.mapToDomainModel(movieDto)

        assertEquals(null, result)
    }
}