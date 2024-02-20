package com.devsu.streaming_ui_tv.radio.radio_player

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsu.navigation.NavigationManager
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_ui_tv.youtube.youtube_video_player.YouTubeVideoPlayerUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ARG_TAG = "ARG_TAG"

@HiltViewModel
class RadioPlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    var state by mutableStateOf(RadioPlayerState())

    private val _uiEvent = Channel<RadioPlayerUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        Log.v("JordanRA", "RadioPlayerViewModel: ${savedStateHandle.keys()}")
        var stationuuid: String? = null
        var name: String? = null
        var urlResolved: String? = null
        var favicon: String? = null

        savedStateHandle.get<String>(StreamingDirections.KEY_RADIO_STATION_UUID)?.let { it ->
            stationuuid = it
        }
        savedStateHandle.get<String>(StreamingDirections.KEY_RADIO_NAME)?.let { it ->
            name = it
        }
        /*savedStateHandle.get<List<String>>(StreamingDirections.KEY_RADIO_TAG_LIST)?.let { it ->
            Log.v("JordanRA", "KEY_RADIO_TAG_LIST: $it")
        }*/
        savedStateHandle.get<String>(StreamingDirections.KEY_RADIO_URL_RESOLVED)?.let { it ->
           urlResolved = it
        }
        savedStateHandle.get<String>(StreamingDirections.KEY_RADIO_FAVICON)?.let { it ->
            favicon = it
        }
        state = state.copy(
            stationuuid = stationuuid,
            name = name,
            urlResolved = urlResolved,
            favicon = favicon
        )
        Log.v("JordanRA", "finish init state: $state")
    }

    fun onEvent(event: RadioPlayerEvent){
        when(event){
            is RadioPlayerEvent.OnSetMediaMetadata -> onSetMediaMetadata(event)
            is RadioPlayerEvent.OnSetPlayWhenReady -> onSetPlayWhenReady(event)
            is RadioPlayerEvent.OnShowError -> onShowError(event)
        }
    }

    private fun onSetPlayWhenReady(event: RadioPlayerEvent.OnSetPlayWhenReady) {
        state = state.copy(
            playWhenReady = event.playWhenReady
        )
    }

    private fun onSetMediaMetadata(event: RadioPlayerEvent.OnSetMediaMetadata) {
        state = state.copy(
            exoMediaMetadata = event.mediaMetadata
        )
    }

    private fun onShowError(event: RadioPlayerEvent.OnShowError) {
        viewModelScope.launch {
            _uiEvent.send(RadioPlayerUiEvent.ShowError(event.message))
        }
    }
}

sealed class RadioPlayerUiEvent{
    data class ShowError(val message: String): RadioPlayerUiEvent()
}