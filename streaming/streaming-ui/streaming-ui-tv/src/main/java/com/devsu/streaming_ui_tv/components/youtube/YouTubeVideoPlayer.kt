@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.devsu.streaming_ui_tv.components.youtube

import android.content.Context
import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.devsu.streaming_ui_tv.util.findActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun YoutubeVideoPlayer(
    context: Context,
    modifier: Modifier = Modifier,
    videoId: String,
    isPlaying: Boolean,
    onVideoError: (PlayerConstants.PlayerError) -> Unit = {},
){
    val focusRequester = remember { FocusRequester() }

    var player by remember {
        mutableStateOf<YouTubePlayer?>(null)
    }
    var defaultPlayerUiController by remember {
        mutableStateOf<DefaultPlayerUiControllerTV?>(null)
    }

    val playerView = YouTubePlayerView(context)
    val playerStateListener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            Log.v("JordanRA", "onReady")
            player = youTubePlayer
            defaultPlayerUiController = DefaultPlayerUiControllerTV(playerView, youTubePlayer)
            defaultPlayerUiController?.showFullscreenButton(false)
            defaultPlayerUiController?.showPlayPauseButton(true)
            playerView.setCustomPlayerUi(defaultPlayerUiController!!.rootView)
            youTubePlayer.cueVideo(videoId, 0f)
        }
    }
    val playerBuilder = IFramePlayerOptions.Builder().apply {
        controls(0)
        fullscreen(0)
        autoplay(0)
        rel(1)
    }
    AndroidView(
        modifier = modifier
            .focusable(true)
            .focusRequester(focusRequester)
            .onKeyEvent { event ->
                if (event.key == Key.DirectionCenter && event.type == KeyEventType.KeyUp) {
                    Log.v("JordanRA", "event DirectionCenter")
                    if(defaultPlayerUiController?.isPlaying != true){
                        player?.play()
                    }else{
                        player?.pause()
                    }
                    true
                } else {
                    false
                }
            },
        factory = {
            playerView.apply {
                enableAutomaticInitialization = false
                initialize(playerStateListener, playerBuilder.build())
            }
        }
    )
    DisposableEffect(key1 = Unit, effect = {
        context.findActivity() ?: return@DisposableEffect onDispose {}
        focusRequester.requestFocus()
        onDispose {
            playerView.removeYouTubePlayerListener(playerStateListener)
            playerView.release()
            player = null
        }
    })
    DisposableEffect(key1 = isPlaying) {
        Log.v("JordanRA", "DisposableEffect: $isPlaying - player == null: ${player == null}")
        if (isPlaying) {
            player?.play()
        } else {
            player?.pause()
        }
        onDispose { player?.pause() }
    }
}