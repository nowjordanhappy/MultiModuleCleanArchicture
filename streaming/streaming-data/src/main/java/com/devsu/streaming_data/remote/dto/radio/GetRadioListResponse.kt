package com.devsu.streaming_data.remote.dto.radio

import com.google.gson.annotations.SerializedName

typealias GetRadioListResponse = List<RadioDto>

data class RadioDto (
    val changeuuid: String? = null,
    val stationuuid: String? = null,
    val name: String? = null,
    val url: String? = null,
    @SerializedName("url_resolved")
    val urlResolved: String? = null,
    val homepage: String? = null,
    val favicon: String? = null,
    val tags: String? = null,
    val country: String? = null,
    val countrycode: String? = null,
    val state: String? = null,
    val language: String? = null,
    val votes: Long? = null,
    val codec: String? = null,
)