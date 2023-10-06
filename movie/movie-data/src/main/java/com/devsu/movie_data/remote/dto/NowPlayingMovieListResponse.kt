package com.devsu.movie_data.remote.dto

import com.google.gson.annotations.SerializedName

data class NowPlayingMovieListResponse (
    val dates: Dates? = null,
    val page: Long? = null,
    val results: List<MovieDto>? = null,

    @SerializedName("total_pages")
    val totalPages: Long? = null,

    @SerializedName("total_results")
    val totalResults: Long? = null
)

data class Dates (
    val maximum: String? = null,
    val minimum: String? = null
)

data class MovieDto (
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("genre_ids")
    val genreIDS: List<Long>? = null,

    val id: Long? = null,

    @SerializedName("original_language")
    val originalLanguage: OriginalLanguage? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    val overview: String? = null,
    val popularity: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    val title: String? = null,
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("vote_count")
    val voteCount: Long? = null
)

enum class OriginalLanguage(val value: String) {
    @SerializedName("en") En("en"),
    @SerializedName("is") Is("is");
}