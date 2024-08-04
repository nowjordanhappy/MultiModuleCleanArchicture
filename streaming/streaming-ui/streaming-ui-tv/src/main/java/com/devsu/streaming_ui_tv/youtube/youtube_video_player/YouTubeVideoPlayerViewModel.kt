package com.devsu.streaming_ui_tv.youtube.youtube_video_player

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsu.navigation.StreamingDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YouTubeVideoPlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    var state by mutableStateOf(YouTubeVideoPlayerState())

    private val _uiEvent = Channel<YouTubeVideoPlayerUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        var videoId: String? = null
        var videoTitle: String? = null
        if(savedStateHandle.contains(StreamingDirections.KEY_YOUTUBE_VIDEO_ID)){
            savedStateHandle.get<String>(StreamingDirections.KEY_YOUTUBE_VIDEO_ID)?.let { id ->
                videoId = id
            }
        }
        if(savedStateHandle.contains(StreamingDirections.KEY_YOUTUBE_VIDEO_TITLE)){
            savedStateHandle.get<String>(StreamingDirections.KEY_YOUTUBE_VIDEO_TITLE)?.let { title ->
                videoTitle = title
            }
        }
        onEvent(YouTubeVideoPlayerEvent.OnSetInitialData(videoId, videoTitle))
    }

    fun onEvent(event: YouTubeVideoPlayerEvent){
        when(event){
            is YouTubeVideoPlayerEvent.OnSetInitialData -> onSetInitialData(event)
            is YouTubeVideoPlayerEvent.OnShowError -> onShowError(event)
        }
    }

    private fun onSetInitialData(event: YouTubeVideoPlayerEvent.OnSetInitialData) {
        state = state.copy(
            videoId = event.videoId,
            videoTitle = event.videoTitle
        )
    }

    private fun onShowError(event: YouTubeVideoPlayerEvent.OnShowError) {
        viewModelScope.launch {
            _uiEvent.send(YouTubeVideoPlayerUiEvent.ShowError(event.message))
        }
    }
}

sealed class YouTubeVideoPlayerUiEvent{
    data class ShowError(val message: String): YouTubeVideoPlayerUiEvent()
}