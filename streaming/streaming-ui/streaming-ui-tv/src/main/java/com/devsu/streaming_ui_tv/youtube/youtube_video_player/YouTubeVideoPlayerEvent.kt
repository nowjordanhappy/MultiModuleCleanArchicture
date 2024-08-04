package com.devsu.streaming_ui_tv.youtube.youtube_video_player

sealed interface YouTubeVideoPlayerEvent{
    data class OnSetInitialData(val videoId: String?, val videoTitle: String?):
        YouTubeVideoPlayerEvent
    data class OnShowError(val message: String): YouTubeVideoPlayerEvent
}
