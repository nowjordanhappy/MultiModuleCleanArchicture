package com.devsu.movie_ui_mobile

sealed class MovieListEvent{
    object OnGetMovies: MovieListEvent()
}