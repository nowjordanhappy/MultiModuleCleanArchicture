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
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Metadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.extractor.metadata.icy.IcyInfo
import androidx.media3.extractor.metadata.id3.ApicFrame
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.devsu.streaming_ui_tv.radio.components.RadioImage
import com.devsu.streaming_ui_tv.radio.radio_by_tag.RadioByTagViewModel
import com.devsu.streaming_ui_tv.util.conditional

@Composable
@UnstableApi
fun RadioPlayerScreen(
    scaffoldState: SnackbarHostState,
    viewModel: RadioPlayerViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val state = viewModel.state

    val context = LocalContext.current

    Log.v("JordanRA", "trying to play1: urlResolved: ${state.urlResolved}")
    state.urlResolved?.let { urlResolved->
        val exoPlayer = remember {
            ExoPlayer.Builder(context)
                .setMediaSourceFactory(DefaultMediaSourceFactory(context))
                .build() }

        LaunchedEffect(key1 = Unit) {

            Log.v("JordanRA", "trying to play: urlResolved: $urlResolved")
            val mediaItem = MediaItem.fromUri(urlResolved)
            //val mediaItem = MediaItem.fromUri("https://servidor19.brlogic.com:7300/live")
            //val mediaItem = MediaItem.fromUri(Uri.parse("https://servidor19.brlogic.com:7300/live"))

            exoPlayer.setMediaItem(mediaItem)

            val listener = playbackStateListener{ mediaMetadata->
                viewModel.onEvent(RadioPlayerEvent.OnSetMediaMetadata(mediaMetadata))
            }
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

                Log.v("JordanRA", "width: ${configuration.screenWidthDp}")

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
                        contentDescription = if (state.isPlaying) "Pause" else "Play")
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
            /*val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    exoPlayer.playWhenReady = true
                    isPlaying = true
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    exoPlayer.playWhenReady = false
                    isPlaying = false
                }
            }*/
            //lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                //lifecycleOwner.lifecycle.removeObserver(observer)
                exoPlayer.release()
            }
        }
    }
}

private fun playbackStateListener(updateMetadataHandler: ((MediaMetadata)->Unit)) = @UnstableApi object : Player.Listener {
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
        updateMetadataHandler.invoke(mediaMetadata)
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

    override fun onMetadata(mediaMetadata: Metadata) {
        super.onMetadata(mediaMetadata)
        Log.d("JordanRA", "onMetadata length: ${mediaMetadata.length()}")
        if(mediaMetadata.length() > 0){
            /*val entry = mediaMetadata.get(0)
            // Check for ICY metadata (Shoutcast/Icecast)
            if (entry is IcyInfo) {
                Log.d("JordanRA", "onMetadata mediaMetadata IcyInfo")
                val icyInfo = entry as IcyInfo
                val title = icyInfo.title
                val url = icyInfo.url
                // Handle title and URL
            }

            // Check for ID3 metadata (e.g., for artwork)
            if (entry is ApicFrame) {
                Log.d("JordanRA", "onMetadata mediaMetadata ApicFrame")
                val apicFrame = entry as ApicFrame
                val mimeType = apicFrame.mimeType
                val description = apicFrame.description
                val imageBytes = apicFrame.pictureData
                // Handle artwork data
            }
            Log.d("JordanRA", "onMetadata mediaMetadata: ${mediaMetadata[0]}")*/
        }
    }

    override fun onPlaylistMetadataChanged(mediaMetadata: MediaMetadata) {
        super.onPlaylistMetadataChanged(mediaMetadata)
        /*Log.d("JordanRA", "onPlaylistMetadataChanged albumArtist: ${mediaMetadata.albumArtist}")
        Log.d("JordanRA", "onPlaylistMetadataChanged albumTitle: ${mediaMetadata.albumTitle}")
        Log.d("JordanRA", "onPlaylistMetadataChanged title: ${mediaMetadata.title}")
        Log.d("JordanRA", "onPlaylistMetadataChanged subtitle: ${mediaMetadata.subtitle}")
        Log.d("JordanRA", "onPlaylistMetadataChanged artist: ${mediaMetadata.artist}")
        Log.d("JordanRA", "onPlaylistMetadataChanged genre: ${mediaMetadata.genre}")
        Log.d("JordanRA", "onPlaylistMetadataChanged artworkUri null: ${mediaMetadata.artworkUri == null}")
        Log.d("JordanRA", "onPlaylistMetadataChanged artworkData null: ${mediaMetadata.artworkData == null}")
        Log.d("JordanRA", "onPlaylistMetadataChanged artworkDataType null: ${mediaMetadata.artworkDataType == null}")*/
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
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