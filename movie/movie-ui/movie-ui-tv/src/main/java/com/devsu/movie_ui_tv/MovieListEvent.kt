package com.devsu.movie_ui_tv

sealed class MovieListEvent{
    object OnGetMovies: MovieListEvent()
}