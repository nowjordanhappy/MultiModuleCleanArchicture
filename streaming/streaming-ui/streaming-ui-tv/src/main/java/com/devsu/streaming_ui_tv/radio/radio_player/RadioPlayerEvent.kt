package com.devsu.streaming_ui_tv.radio.radio_player

import androidx.media3.common.MediaMetadata

sealed interface RadioPlayerEvent{
    data class OnSetMediaMetadata(val mediaMetadata: MediaMetadata?): RadioPlayerEvent
    data class OnSetPlayWhenReady(val playWhenReady: Boolean): RadioPlayerEvent
    data class OnShowError(val message: String): RadioPlayerEvent
}
