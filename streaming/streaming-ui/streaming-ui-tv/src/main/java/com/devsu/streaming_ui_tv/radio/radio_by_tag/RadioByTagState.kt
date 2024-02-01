package com.devsu.streaming_ui_tv.radio.radio_by_tag

import com.devsu.core_ui.model.ProgressBarState
import com.devsu.streaming_domain.model.Radio

data class RadioByTagState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val radioList: List<Radio> = emptyList(),
    val page: Int = 1,
    val limit: Int = 20
)
