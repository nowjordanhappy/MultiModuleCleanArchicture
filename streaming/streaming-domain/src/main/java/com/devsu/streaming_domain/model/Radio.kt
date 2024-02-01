package com.devsu.streaming_domain.model

data class Radio(
    val changeuuid: String,
    val stationuuid: String,
    val name: String,
    val urlResolved: String,
    val homepage: String?,
    val favicon: String?,
    val tags: List<String>,
    val countrycode: String?,
    val state: String?,
    val language: String,
    val votes: Long,
    val codec: String?,
)
