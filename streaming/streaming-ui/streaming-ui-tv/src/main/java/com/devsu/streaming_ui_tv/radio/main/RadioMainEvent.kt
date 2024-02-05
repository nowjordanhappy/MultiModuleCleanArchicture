package com.devsu.streaming_ui_tv.radio.main

import com.devsu.streaming_domain.model.Country
import com.devsu.streaming_domain.model.Tag

sealed interface RadioMainEvent{
    object OnGetCurrentUser: RadioMainEvent
    object OnGetPopularTags: RadioMainEvent
    object OnGetPopularCountries: RadioMainEvent
    data class OnNavigateToRadioListByTag(val tag: Tag): RadioMainEvent
    data class OnNavigateToRadioListByCountry(val country: Country): RadioMainEvent
}
