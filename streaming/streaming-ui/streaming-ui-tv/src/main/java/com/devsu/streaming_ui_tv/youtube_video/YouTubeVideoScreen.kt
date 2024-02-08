package com.devsu.streaming_ui_tv.youtube_video

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_ui_tv.components.LoadingView
import com.devsu.streaming_ui_tv.components.TvContainer
import com.devsu.streaming_ui_tv.radio.components.RadioList
import com.devsu.streaming_ui_tv.radio.main.components.Section
import com.devsu.streaming_ui_tv.youtube_video.components.YouTubeVideoList
import com.devsu.streaming_ui_tv.youtube_video_player.YouTubeVideoPlayerUiEvent

@Composable
fun YouTubeVideoScreen(
    scaffoldState: SnackbarHostState,
    viewModel: YouTubeVideoViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event->
            when(event){
                is YouTubeVideoUiEvent.ShowError -> {
                    scaffoldState.showSnackbar(event.message)
                }
            }
        }
    }

    TvContainer(
        modifier = Modifier,
        color = Color.Blue
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Section(
                title = state.channelName ?: "Channel Video",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .padding(top = 20.dp)
            )

            if(state.progressBarState == ProgressBarState.Idle){
                YouTubeVideoList(
                    modifier = Modifier.fillMaxSize(),
                    items = state.youTubeVideos,
                    onSelectItem = { video ->
                        viewModel.onEvent(YouTubeVideoEvent.OnNavigateToYouTubePlayer(video))
                    }
                )
            }else{
                LoadingView()
            }
        }
    }
}