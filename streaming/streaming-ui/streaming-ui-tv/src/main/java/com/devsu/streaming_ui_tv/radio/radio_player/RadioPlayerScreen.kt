@file:OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)

package com.devsu.streaming_ui_tv.radio.radio_player

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.devsu.streaming_ui_tv.R
import com.devsu.streaming_ui_tv.radio.components.RadioImage
import com.devsu.streaming_ui_tv.util.conditional

@Composable
@UnstableApi
fun RadioPlayerScreen(
    scaffoldState: SnackbarHostState,
    viewModel: RadioPlayerViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event->
            when(event){
                is RadioPlayerUiEvent.ShowError -> {
                    scaffoldState.showSnackbar(event.message)
                }
            }
        }
    }

    val context = LocalContext.current

    state.urlResolved?.let { urlResolved->
        val exoPlayer = remember {
            ExoPlayer.Builder(context)
                .setMediaSourceFactory(DefaultMediaSourceFactory(context))
                .build() }

        LaunchedEffect(key1 = Unit) {
            val mediaItem = MediaItem.fromUri(urlResolved)
            exoPlayer.setMediaItem(mediaItem)

            val listener = playbackStateListener(
                onRadioPlayerMetadataChanged = { mediaMetadata->
                    viewModel.onEvent(RadioPlayerEvent.OnSetMediaMetadata(mediaMetadata))
                },
                onRadioPlayerError = {
                    viewModel.onEvent(RadioPlayerEvent.OnShowError(it.message ?: context.getString(com.devsu.core_ui.R.string.unknown_error)))
                }
            )
            exoPlayer.addListener(listener)

            exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
            exoPlayer.playWhenReady = state.playWhenReady

            if (state.playWhenReady) {
                exoPlayer.prepare()
            }

            Log.v("JordanRA","setting playWhenReady: ${state.playWhenReady}")
        }

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val image: Uri? = state.exoMediaMetadata?.artworkUri ?: (state.favicon?.let { Uri.parse(it) } ?: kotlin.run { null })

                PlayerArtwork(
                    image = image,
                )

                val configuration = LocalConfiguration.current

                Spacer(modifier = Modifier.height(5.dp))

                Column (
                    modifier = Modifier
                        .width((configuration.screenWidthDp * 0.3f).dp)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .conditional(state.playWhenReady, ifTrue = {
                                basicMarquee(
                                    iterations = Int.MAX_VALUE
                                )
                            }),
                        text = state.exoMediaMetadata?.title?.toString() ?: "",
                        textAlign = TextAlign.Center,
                        style = TextStyle.Default.copy(
                            color = Color.White
                        ),
                        maxLines = 1
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = state.exoMediaMetadata?.genre?.toString() ?: "",
                        textAlign = TextAlign.Center,
                        style = TextStyle.Default.copy(
                            color = Color.White
                        ),
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    Log.v("JordanRA","playWhenReady: ${state.playWhenReady}")
                    val newPlayWhenReady = !state.playWhenReady
                    viewModel.onEvent(RadioPlayerEvent.OnSetPlayWhenReady(newPlayWhenReady))

                    exoPlayer.playWhenReady = newPlayWhenReady
                    if (newPlayWhenReady) {
                        exoPlayer.prepare()
                    }else{
                        exoPlayer.stop()
                    }
                }) {
                    Icon(if (state.playWhenReady) Icons.Default.Pause else
                        Icons.Default.PlayArrow,
                        contentDescription = if (state.isPlaying) stringResource(R.string.pause) else stringResource(
                            R.string.play
                        )
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = state.name ?: "",
                style = TextStyle.Default.copy(
                    color = Color.White,
                    fontSize = 50.sp
                ),
                maxLines = 1
            )
        }

        DisposableEffect(key1 = lifecycleOwner) {
            onDispose {
                exoPlayer.release()
            }
        }
    }
}

private fun playbackStateListener(
    onRadioPlayerMetadataChanged: ((MediaMetadata)->Unit),
    onRadioPlayerError: (PlaybackException)-> Unit,
    ) = @UnstableApi object : Player.Listener {
    override fun onPlaybackStateChanged(playbackState: Int) {
        val stateString: String = when (playbackState) {
            ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
            ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
            ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
            ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
            else -> "UNKNOWN_STATE             -"
        }
        Log.d("JordanRA", "changed state to $stateString")
    }

    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
        super.onMediaMetadataChanged(mediaMetadata)
        onRadioPlayerMetadataChanged.invoke(mediaMetadata)
        Log.d("JordanRA", "mediadata albumArtist: ${mediaMetadata.albumArtist}")
        Log.d("JordanRA", "mediadata albumTitle: ${mediaMetadata.albumTitle}")
        Log.d("JordanRA", "mediadata title: ${mediaMetadata.title}")
        Log.d("JordanRA", "mediadata subtitle: ${mediaMetadata.subtitle}")
        Log.d("JordanRA", "mediadata artist: ${mediaMetadata.artist}")
        Log.d("JordanRA", "mediadata genre: ${mediaMetadata.genre}")
        Log.d("JordanRA", "mediadata artworkUri null: ${mediaMetadata.artworkUri == null}")
        Log.d("JordanRA", "mediadata artworkData null: ${mediaMetadata.artworkData == null}")
        Log.d("JordanRA", "mediadata artworkDataType null: ${mediaMetadata.artworkDataType == null}")
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        onRadioPlayerError.invoke(error)
        Log.d("JordanRA", "onPlayerError ${error.localizedMessage}")
    }
}

@Composable
fun PlayerArtwork(
    image: Uri?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.DarkGray.copy(alpha = 0.8f))
    ) {
        RadioImage(
            image = image,
            modifier = Modifier
                .size(200.dp),
        )
    }
}