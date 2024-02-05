package com.devsu.streaming_ui_tv.radio.main

import com.devsu.streaming_domain.model.Country
import com.devsu.streaming_domain.model.Tag
import com.devsu.streaming_domain.model.User

data class RadioMainState(
    val userSelected: User? = null,
    val popularTags: List<Tag> = emptyList(),
    val popularCountries: List<Country> = emptyList(),
)
