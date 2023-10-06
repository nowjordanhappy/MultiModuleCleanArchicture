package com.devsu.movie_data.mapper

import com.devsu.core.Constants
import com.devsu.movie_data.remote.dto.MovieDto
import com.devsu.movie_domain.model.Movie

class MovieDtoMapper{
    fun mapToDomainModel(model: MovieDto): Movie? {
        return Movie(
            id = model.id ?: return null,
            title = model.title ?: "Unknown",
            originalTitle = model.originalTitle ?: "Unknown",
            overview = model.overview ?: "No overview",
            photoUrl = getImageUrl(model.backdropPath),
            posterUrl = getImageUrl(model.posterPath),
            releaseDate = model.releaseDate ?: "",
            voteAverage = model.voteAverage,
            isForAdult = model.adult == true
        )
    }

    private fun getImageUrl(photoUrl: String?): String{
        return photoUrl?.let {photo ->
            Constants.IMAGE_BASE_URL + photo
        } ?: kotlin.run {
            ""
        }
    }
}