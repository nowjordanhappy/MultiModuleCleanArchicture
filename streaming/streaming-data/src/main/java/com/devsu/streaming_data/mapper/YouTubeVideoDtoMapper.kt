package com.devsu.streaming_data.mapper

import com.devsu.core.DateFormatType
import com.devsu.core.DateUtils
import com.devsu.streaming_data.remote.dto.youtube.YouTubeVideoDto
import com.devsu.streaming_domain.model.YouTubeVideo

class YouTubeVideoDtoMapper {
    fun mapToDomainModel(model: YouTubeVideoDto): YouTubeVideo? {
        return YouTubeVideo(
            videoId = model.id?.videoID ?: return null,
            datePublished = DateUtils.convertDateFormat(model.snippet?.publishedAt, inputFormat = DateFormatType.Full, outputFormat = DateFormatType.ShortAMPM) ?: "",
            title = model.snippet?.title ?: "unknown",
            description = model.snippet?.description ?: "",
            thumbnail = model.snippet?.thumbnails?.high?.url
        )
    }
}