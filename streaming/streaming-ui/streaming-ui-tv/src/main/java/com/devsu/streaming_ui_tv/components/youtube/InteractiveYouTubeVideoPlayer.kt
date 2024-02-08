package com.devsu.streaming_ui_tv.components.youtube

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants

@Composable
fun InteractiveYouTubeVideoPlayer(
    context: Context,
    videoId: String,
    isPlaying: Boolean,
    onVideoError: (PlayerConstants.PlayerError) -> Unit = {},
) {
    Box(Modifier.fillMaxSize()) {
        YoutubeVideoPlayer(
            context = context,
            modifier = Modifier
                .fillMaxSize(),
            videoId = videoId,
            isPlaying = isPlaying,
            onVideoError = onVideoError,
        )
    }
}