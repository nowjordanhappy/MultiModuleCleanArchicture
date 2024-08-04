package com.devsu.streaming_ui_tv.radio.radio_player

import androidx.media3.common.MediaMetadata
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.streaming_domain.model.Radio

data class RadioPlayerState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val stationuuid: String? = null,
    val name: String? = null,
    val urlResolved: String? = null,
    val favicon: String? = null,
    val exoMediaMetadata: MediaMetadata? = null,
    val isPlaying: Boolean = false,
    val playWhenReady: Boolean = false,
)
