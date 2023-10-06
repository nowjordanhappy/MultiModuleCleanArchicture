package com.devsu.movie_ui_mobile

import com.devsu.core.Constants
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.movie_domain.model.Movie

data class MovieListState (
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val movies: List<Movie> = emptyList(),
    val page: Int = 1,
    val language: String = Constants.DEFAULT_LANGUAGE
)