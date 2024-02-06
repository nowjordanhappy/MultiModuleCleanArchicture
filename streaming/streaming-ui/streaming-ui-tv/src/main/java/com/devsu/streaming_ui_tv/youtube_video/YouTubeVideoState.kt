package com.devsu.streaming_ui_tv.youtube_video

import com.devsu.core_ui.model.ProgressBarState
import com.devsu.streaming_domain.model.User
import com.devsu.streaming_domain.model.YouTubeVideo

data class YouTubeVideoState (
    val userSelected: User? = null,
    val channelId: String? = null,
    val channelName: String? = null,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val youTubeVideos: List<YouTubeVideo> = emptyList(),
)
