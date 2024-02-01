package com.devsu.streaming_data.remote.dto.radio

typealias GetTagRadioListResponse = List<TagRadioDto>

data class TagRadioDto(
    val name: String,
    val stationCount: Int
)