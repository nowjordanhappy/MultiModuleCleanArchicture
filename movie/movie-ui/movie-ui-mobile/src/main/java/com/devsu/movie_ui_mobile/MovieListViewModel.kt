package com.devsu.movie_ui_mobile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.UIComponent
import com.devsu.core_ui.utils.ManagerConnection
import com.devsu.movie_domain.use_case.GetNowPlayingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val managerConnection: ManagerConnection
): ViewModel(){
    var state by mutableStateOf(MovieListState())

    private val _uiEvent = Channel<MovieListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(MovieListEvent.OnGetMovies)
    }

    fun onEvent(event: MovieListEvent){
        when(event){
            MovieListEvent.OnGetMovies -> onGetMovies()
        }
    }

    private fun onGetMovies() {
        getNowPlayingMovies
            .execute(
                language = state.language,
                page = state.page,
                isNetworkAvailable = managerConnection.isNetworkAvailable()
            )
            .onEach { dataState->
                when(dataState){
                    is DataState.Data -> {
                        state = state.copy(
                            movies = dataState.data ?: emptyList()
                        )
                    }
                    is DataState.Loading -> {
                        state = state.copy(
                            progressBarState = dataState.progressBarState
                        )
                    }
                    is DataState.Response -> {
                        when(dataState.uiComponent){
                            is UIComponent.None -> {
                                val message = (dataState.uiComponent as UIComponent.None).message
                                _uiEvent.send(MovieListUiEvent.ShowError(message))
                            }
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}

sealed class MovieListUiEvent{
    object SuccessSearch: MovieListUiEvent()
    data class ShowError(val message: String): MovieListUiEvent()
}