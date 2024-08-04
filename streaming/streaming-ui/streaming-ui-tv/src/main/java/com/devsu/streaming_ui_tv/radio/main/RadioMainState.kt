package com.devsu.streaming_ui_tv.radio.main

import com.devsu.streaming_domain.model.Country
import com.devsu.streaming_domain.model.Tag
import com.devsu.streaming_domain.model.User
import com.devsu.streaming_domain.model.YouTubeChannel

data class RadioMainState(
    val userSelected: User? = null,
    val popularTags: List<Tag> = emptyList(),
    val popularCountries: List<Country> = emptyList(),
    val popularYouTubeChannels: List<YouTubeChannel> = emptyList(),
    val youtubeChannelSelected: YouTubeChannel? = null,
    val countrySelected: Country? = null,
    val popularTagSelected: Tag? = null,
)
