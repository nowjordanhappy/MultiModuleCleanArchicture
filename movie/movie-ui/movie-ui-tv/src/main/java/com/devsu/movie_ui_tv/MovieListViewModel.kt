package com.devsu.movie_ui_tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsu.core.Constants
import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.core_ui.model.UIComponent
import com.devsu.core_ui.utils.ManagerConnection
import com.devsu.core_ui.utils.ScreenHelper
import com.devsu.movie_domain.model.Movie
import com.devsu.movie_domain.use_case.GetNowPlayingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val managerConnection: ManagerConnection
): ViewModel(){

    private val _progressBarState: MutableStateFlow<ProgressBarState> = MutableStateFlow(ProgressBarState.Idle)
    val progressBarState: StateFlow<ProgressBarState> = _progressBarState.asStateFlow()

    private val _photoList = MutableStateFlow(emptyList<Movie>())
    val photoList: StateFlow<List<Movie>> = _photoList.asStateFlow()

    private val _page = MutableStateFlow(1)

    private val _uiEvent = Channel<MovieListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val language: String = Constants.DEFAULT_LANGUAGE
    val numColumns = 3
    val cardWidth = (0.8 * ScreenHelper.getScreenWidth() / numColumns).toInt()
    val cardHeight = (cardWidth * 0.6).toInt()

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
                language = language,
                page = _page.value,
                isNetworkAvailable = managerConnection.isNetworkAvailable()
            )
            .onEach { dataState->
                when(dataState){
                    is DataState.Data -> {
                        _photoList.value = dataState.data ?: emptyList()
                    }
                    is DataState.Loading -> {
                        _progressBarState.value = dataState.progressBarState
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