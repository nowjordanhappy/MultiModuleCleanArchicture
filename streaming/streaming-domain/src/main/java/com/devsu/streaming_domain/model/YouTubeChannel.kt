package com.devsu.streaming_domain.model

data class YouTubeChannel(
    val channelId: String,
    val name: String,
    val title: String,
    val subtitle: String,
    val background: String,
    val logo: String
)