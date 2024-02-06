package com.devsu.streaming_data.mapper

import com.devsu.core.getValueOrNull
import com.devsu.streaming_data.remote.dto.youtube.YouTubeVideoDto
import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_domain.model.YouTubeVideo

class YouTubeVideoDtoMapper {
    fun mapToDomainModel(model: YouTubeVideoDto): YouTubeVideo? {
        return YouTubeVideo(
            videoId = model.id?.videoID ?: return null,
            datePublished = model.snippet?.publishedAt ?: "",
            title = model.snippet?.title ?: "unknown",
            description = model.snippet?.description ?: "",
            thumbnail = model.snippet?.thumbnails?.high?.url
        )
    }
}