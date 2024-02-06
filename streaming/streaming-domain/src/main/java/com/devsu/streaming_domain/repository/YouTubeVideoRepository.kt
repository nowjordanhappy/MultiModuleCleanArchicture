package com.devsu.streaming_domain.repository

import com.devsu.streaming_domain.model.User
import com.devsu.streaming_domain.model.YouTubeVideo

interface YouTubeVideoRepository {
    suspend fun searchVideos(channelId: String): List<YouTubeVideo>
}