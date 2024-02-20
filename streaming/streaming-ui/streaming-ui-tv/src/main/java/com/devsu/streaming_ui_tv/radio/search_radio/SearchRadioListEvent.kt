package com.devsu.streaming_ui_tv.radio.search_radio

import com.devsu.streaming_domain.model.Radio

sealed interface SearchRadioListEvent{
    data class OnSearchRadioList(val key: String?, val value: String?): SearchRadioListEvent
    data class OnNavigateToToRadioPlayer(val radio: Radio): SearchRadioListEvent
    data class OnChangeRadio(val radio: Radio): SearchRadioListEvent
}
