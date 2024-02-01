@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.devsu.streaming_ui_tv.components

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.devsu.streaming_ui_tv.util.findActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubeVideoPlayer(
    context: Context,
    videoId: String
) {

    val webView = WebView(LocalContext.current).apply {
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        webViewClient = WebViewClient()
    }

    /*val lifecycleOwner = LocalLifecycleOwner.current

    val player = YouTubePlayerView(context)

    lifecycleOwner.lifecycle.addObserver(player)*/

    val htmlData = getHTMLData(videoId)

    Column(Modifier.fillMaxSize()) {
        /*AndroidView(factory = { player }) { view ->
            view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }*/
        YoutubeVideoPlayer1(
            modifier = Modifier.fillMaxSize()
            ,
            videoId = videoId
        )
        /*Row{
            Button(onClick = {
                webView.loadUrl("javascript:playVideo();")
            }) {
                Text(text = "Play Video")
            }
            Button(
                onClick = {
                    print("here loading")
                    webView.loadUrl("javascript:pauseVideo();")
                }) {
                Text(text = "Pause Video")
            }
            Button(onClick = {
                webView.loadUrl("javascript:seekTo(60);")
            }) {
                Text(text = "Seek Video")
            }
        }*/
    }
}

@Composable
fun YoutubeVideoPlayer1(
    modifier: Modifier = Modifier,
    //youtubeURL: String?,
    videoId: String,
    isPlaying: (Boolean) -> Unit = {},
    isLoading: (Boolean) -> Unit = {},
    onVideoEnded: () -> Unit = {}
){
    val mContext = LocalContext.current
    //val mLifeCycleOwner = LocalLifecycleOwner.current
    val mLifeCycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    //val videoId = splitLinkForVideoId(youtubeURL)
    var player : com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer ?= null
    val playerFragment = YouTubePlayerView(mContext)
    val playerStateListener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
            super.onReady(youTubePlayer)
            player = youTubePlayer
            youTubePlayer.loadVideo(videoId, 0f)
        }

        override fun onStateChange(
            youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
            state: PlayerConstants.PlayerState
        ) {
            super.onStateChange(youTubePlayer, state)
            when(state){
                PlayerConstants.PlayerState.BUFFERING -> {
                    isLoading.invoke(true)
                    isPlaying.invoke(false)
                }
                PlayerConstants.PlayerState.PLAYING -> {
                    isLoading.invoke(false)
                    isPlaying.invoke(true)
                }
                PlayerConstants.PlayerState.ENDED -> {
                    isPlaying.invoke(false)
                    isLoading.invoke(false)
                    onVideoEnded.invoke()
                }
                else -> {}
            }
        }

        override fun onError(
            youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer,
            error: PlayerConstants.PlayerError
        ) {
            super.onError(youTubePlayer, error)
            //Console.debug("iFramePlayer Error Reason = $error")
        }
    }
    val playerBuilder = IFramePlayerOptions.Builder().apply {
        controls(0)
        fullscreen(0)
        autoplay(0)
        rel(1)
    }
    AndroidView(
        modifier = modifier.background(Color.DarkGray)
            .height(100.dp),
        factory = {
            playerFragment.apply {
                enableAutomaticInitialization = false
                initialize(playerStateListener, playerBuilder.build())
            }
        }
    )
    DisposableEffect(key1 = Unit, effect = {
        mContext.findActivity() ?: return@DisposableEffect onDispose {}
        onDispose {
            playerFragment.removeYouTubePlayerListener(playerStateListener)
            playerFragment.release()
            player = null
        }
    })
    DisposableEffect(mLifeCycleOwner) {
        val lifecycle = mLifeCycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    player?.play()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    player?.pause()
                }
                else -> {
                    //
                }
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

private fun splitLinkForVideoId(
    url: String?
): String {
    return (url!!.split("="))[1]
}

fun getHTMLData(videoId: String): String {
    return """
        <html>
        
            <body style="margin:0px;padding:0px;">
               <div id="player"></div>
                <script>
                    var player;
                    function onYouTubeIframeAPIReady() {
                        player = new YT.Player('player', {
                            height: '450',
                            width: '650',
                            videoId: '$videoId',
                            playerVars: {
                                'playsinline': 1
                            },
                            events: {
                                'onReady': onPlayerReady
                            }
                        });
                    }
                    function onPlayerReady(event) {
                     player.playVideo();
                        // Player is ready
                    }
                    function seekTo(time) {
                        player.seekTo(time, true);
                    }
                      function playVideo() {
                        player.playVideo();
                    }
                    function pauseVideo() {
                        player.pauseVideo();
                    }
                </script>
                <script src="https://www.youtube.com/iframe_api"></script>
            </body>
        </html>
    """.trimIndent()
}