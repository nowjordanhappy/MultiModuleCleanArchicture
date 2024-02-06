package com.devsu.streaming_ui_tv.youtube_video

import com.devsu.streaming_domain.model.Country
import com.devsu.streaming_domain.model.Tag
import com.devsu.streaming_domain.model.YouTubeVideo

sealed interface YouTubeVideoEvent{
    data class OnGetYouTubeVideos(val channelId: String?, val channelName: String?): YouTubeVideoEvent
    data class OnNavigateToYouTubePlayer(val video: YouTubeVideo): YouTubeVideoEvent
}
