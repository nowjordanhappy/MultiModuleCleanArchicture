package com.devsu.streaming_data.repository

import com.devsu.streaming_data.mapper.YouTubeVideoDtoMapper
import com.devsu.streaming_data.remote.YouTubeApi
import com.devsu.streaming_domain.model.YouTubeVideo
import com.devsu.streaming_domain.repository.YouTubeVideoRepository

class YouTubeVideoRepositoryImpl(
    private val apiKey: String,
    private val service: YouTubeApi,
    private val mapper: YouTubeVideoDtoMapper,
): YouTubeVideoRepository {

    override suspend fun searchVideos(channelId: String): List<YouTubeVideo> {
        val params = mapOf(
            "part" to "snippet,id",
            "order" to "date",
            "maxResults" to 20.toString()
        )
        val response = service.searchVideos(
            key = apiKey,
            channelId = channelId,
            parameters = params
        ).items ?: emptyList()

        return response.mapNotNull { mapper.mapToDomainModel(it) }
    }
}