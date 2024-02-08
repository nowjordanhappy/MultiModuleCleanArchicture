package com.devsu.streaming_ui_tv.youtube_video

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.UIComponent
import com.devsu.core_ui.utils.ManagerConnection
import com.devsu.navigation.NavigationCommandSegment
import com.devsu.navigation.NavigationManager
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_domain.use_case.GetYouTubeVideos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class YouTubeVideoViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val savedStateHandle: SavedStateHandle,
    private val getYouTubeVideos: GetYouTubeVideos,
    private val managerConnection: ManagerConnection
): ViewModel() {
    var state by mutableStateOf(YouTubeVideoState())

    private val _uiEvent = Channel<YouTubeVideoUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        var channelId: String? = null
        var channelName: String? = null
        if(savedStateHandle.contains(StreamingDirections.KEY_YOUTUBE_CHANNEL_ID)){
            savedStateHandle.get<String>(StreamingDirections.KEY_YOUTUBE_CHANNEL_ID)?.let { id ->
                channelId = id
            }
        }
        if(savedStateHandle.contains(StreamingDirections.KEY_YOUTUBE_CHANNEL_NAME)){
            savedStateHandle.get<String>(StreamingDirections.KEY_YOUTUBE_CHANNEL_NAME)?.let { name ->
                channelName = name
            }
        }
        Log.v("JordanRA", "YouTubeVM channelId: $channelId - channelName: $channelName")
        onEvent(YouTubeVideoEvent.OnGetYouTubeVideos(channelId, channelName))
    }

    fun onEvent(event: YouTubeVideoEvent){
        when(event){
            is YouTubeVideoEvent.OnGetYouTubeVideos -> onGetYouTubeVideos(event)
            is YouTubeVideoEvent.OnNavigateToYouTubePlayer -> onNavigateToYouTubePlayer(event)
        }
    }

    private fun onGetYouTubeVideos(event: YouTubeVideoEvent.OnGetYouTubeVideos) {
        state = state.copy(
            channelId = event.channelId,
            channelName = event.channelName
        )

        event.channelId?.let {
            getYouTubeVideos
                .execute(
                    channelId = event.channelId,
                    isNetworkAvailable = managerConnection.isNetworkAvailable()
                )
                .onEach { dataState->
                    when(dataState){
                        is DataState.Data -> {
                            state = state.copy(
                                youTubeVideos = dataState.data ?: emptyList()
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
                                    _uiEvent.send(YouTubeVideoUiEvent.ShowError(message))
                                }
                            }
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun onNavigateToYouTubePlayer(event: YouTubeVideoEvent.OnNavigateToYouTubePlayer) {
        navigationManager.navigate(
            NavigationCommandSegment.DefaultNavigation(
                StreamingDirections.youTubeVideoPlayer(
                    videoId = event.video.videoId,
                    videoTitle = event.video.title
                )
            )
        )
    }
}

sealed class YouTubeVideoUiEvent{
    data class ShowError(val message: String): YouTubeVideoUiEvent()
}