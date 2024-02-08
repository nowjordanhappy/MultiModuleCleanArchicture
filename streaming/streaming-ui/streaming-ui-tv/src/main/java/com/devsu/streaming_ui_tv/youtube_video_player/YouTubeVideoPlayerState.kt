package com.devsu.streaming_ui_tv.youtube_video_player

import com.devsu.core_ui.model.ProgressBarState
import com.devsu.streaming_domain.model.User
import com.devsu.streaming_domain.model.YouTubeVideo

data class YouTubeVideoPlayerState (
    val userSelected: User? = null,
    val videoId: String? = null,
    val videoTitle: String? = null,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
)
