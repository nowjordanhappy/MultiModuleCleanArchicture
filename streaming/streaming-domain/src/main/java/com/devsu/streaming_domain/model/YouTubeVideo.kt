package com.devsu.streaming_domain.model

data class YouTubeVideo(
    val videoId: String,
    val datePublished: String,
    val title: String,
    val description: String,
    val thumbnail: String?
)
