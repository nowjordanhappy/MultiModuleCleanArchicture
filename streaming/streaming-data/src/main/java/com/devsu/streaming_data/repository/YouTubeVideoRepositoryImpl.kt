package com.devsu.streaming_data.repository

import android.util.Log
import com.devsu.streaming_data.mapper.RadioDtoMapper
import com.devsu.streaming_data.mapper.YouTubeVideoDtoMapper
import com.devsu.streaming_data.remote.RadioApi
import com.devsu.streaming_data.remote.YouTubeApi
import com.devsu.streaming_data.remote.dto.youtube.YouTubeVideoDto
import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_domain.model.YouTubeVideo
import com.devsu.streaming_domain.repository.RadioRepository
import com.devsu.streaming_domain.repository.YouTubeVideoRepository
import kotlin.math.max

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
        Log.v("JordanRA", "searchVideos: ${params}")
        val response = service.searchVideos(
            key = apiKey,
            channelId = channelId,
            parameters = params
        ).items ?: emptyList()
        Log.v("JordanRA", "searchVideos after: ${params}")

        return response.mapNotNull { mapper.mapToDomainModel(it) }
    }
}