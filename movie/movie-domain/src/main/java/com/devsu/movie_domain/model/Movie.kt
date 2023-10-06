package com.devsu.movie_domain.model

data class Movie(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val photoUrl: String,
    val posterUrl: String,
    val releaseDate: String,
    val voteAverage: Double?,
    val isForAdult: Boolean
)