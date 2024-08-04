package com.devsu.streaming_data.remote.dto.youtube

import com.google.gson.annotations.SerializedName

data class GetYouTubeVideosResponse (
    val kind: String? = null,
    val etag: String? = null,
    val regionCode: String? = null,
    val pageInfo: PageInfo? = null,
    val items: List<YouTubeVideoDto>? = null
)

data class YouTubeVideoDto (
    val etag: String? = null,
    val id: YouTubeVideoId? = null,
    val snippet: Snippet? = null
)

data class YouTubeVideoId (
    @SerializedName("videoId")
    val videoID: String? = null,
)

data class Snippet (
    val publishedAt: String? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnails: Thumbnails? = null,
    val publishTime: String? = null
)

data class Thumbnails (
    val default: Default? = null,
    val medium: Default? = null,
    val high: Default? = null
)

data class Default (
    val url: String? = null,
    val width: Long? = null,
    val height: Long? = null
)

data class PageInfo (
    val totalResults: Long? = null,
    val resultsPerPage: Long? = null
)