package com.devsu.movie_domain.use_case

import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.core_ui.model.UIComponent
import com.devsu.movie_domain.model.Movie
import com.devsu.movie_domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNowPlayingMovies(
    private val repository: MovieRepository
) {
    fun execute(
        language: String,
        page: Int,
        isNetworkAvailable: Boolean
    ): Flow<DataState<List<Movie>>> = flow {
        try {
            emit(DataState.Loading(
                progressBarState = ProgressBarState.Loading
            ))

            if(!isNetworkAvailable){
                emit(DataState.Response(UIComponent.None("Check you connection")))
            }else{
                try {
                    val movies = repository.getNowPlayingMovies(
                        language = language,
                        page = page,
                    )
                    emit(DataState.Data(movies))
                }catch (e: Exception){
                    emit(DataState.Response(UIComponent.None(e.message ?: "API Error")))
                }
            }
        } catch (e: Exception) {
            emit(DataState.Response(UIComponent.None(e.message ?: "Unknown Error")))
        }finally {
            emit(DataState.Loading(
                progressBarState = ProgressBarState.Idle
            ))
        }
    }
}