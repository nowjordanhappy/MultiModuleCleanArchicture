package com.devsu.streaming_ui_tv.radio.radio_by_tag

import com.devsu.streaming_domain.model.Radio

sealed interface RadioByTagEvent{
    data class OnGetRadioList(val tag: String): RadioByTagEvent
    data class OnNavigateToToRadioPlayer(val radio: Radio): RadioByTagEvent
}
