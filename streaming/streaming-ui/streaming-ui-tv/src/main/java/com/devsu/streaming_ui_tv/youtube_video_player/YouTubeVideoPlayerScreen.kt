package com.devsu.streaming_ui_tv.youtube_video_player

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
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

    TvContainer(
        modifier = Modifier,
        color = state.userSelected?.backgroundColor
    ) {
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current


        state.videoId?.let { videoId->
            InteractiveYouTubeVideoPlayer(
                context = context,
                lifecycleOwner = lifecycleOwner,
                videoId = videoId,
                isPlaying = false
            )
        }
    }
}