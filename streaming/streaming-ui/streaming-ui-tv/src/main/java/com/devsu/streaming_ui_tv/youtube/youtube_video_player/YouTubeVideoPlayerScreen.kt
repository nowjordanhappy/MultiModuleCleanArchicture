package com.devsu.streaming_ui_tv.youtube.youtube_video_player

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.devsu.streaming_ui_tv.components.youtube.InteractiveYouTubeVideoPlayer
import com.devsu.streaming_ui_tv.components.TvContainer

@Composable
fun YouTubeVideoPlayerScreen(
    scaffoldState: SnackbarHostState,
    viewModel: YouTubeVideoPlayerViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event->
            when(event){
                is YouTubeVideoPlayerUiEvent.ShowError -> {
                    scaffoldState.showSnackbar(event.message)
                }
            }
        }
    }

    TvContainer(
        modifier = Modifier,
        color = state.userSelected?.backgroundColor
    ) {
        val context = LocalContext.current

        state.videoId?.let { videoId->
            InteractiveYouTubeVideoPlayer(
                context = context,
                videoId = videoId,
                isPlaying = false,
                onVideoError = {
                    viewModel.onEvent(YouTubeVideoPlayerEvent.OnShowError(it.name))
                }
            )
        }
    }
}