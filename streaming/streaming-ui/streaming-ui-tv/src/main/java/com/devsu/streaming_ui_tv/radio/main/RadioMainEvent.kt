package com.devsu.streaming_ui_tv.radio.main

import com.devsu.streaming_domain.model.Country
import com.devsu.streaming_domain.model.Tag
import com.devsu.streaming_domain.model.YouTubeChannel

sealed interface RadioMainEvent{
    object OnGetCurrentUser: RadioMainEvent
    object OnGetPopularTags: RadioMainEvent
    object OnGetPopularCountries: RadioMainEvent
    object OnGetPopularYouTubeChannels: RadioMainEvent
    data class OnNavigateToRadioListByTag(val tag: Tag): RadioMainEvent
    data class OnNavigateToRadioListByCountry(val country: Country): RadioMainEvent
    data class OnNavigateToYouTubeVideo(val youTubeChannel: YouTubeChannel): RadioMainEvent
    data class OnChangeYouTubeChannel(val youTubeChannel: YouTubeChannel?): RadioMainEvent
    data class OnChangeCountry(val country: Country): RadioMainEvent
    data class OnChangePopularTag(val tag: Tag): RadioMainEvent
}
