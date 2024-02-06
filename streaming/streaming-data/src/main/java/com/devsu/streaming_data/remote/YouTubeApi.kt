package com.devsu.streaming_data.remote

import com.devsu.streaming_data.remote.dto.youtube.GetYouTubeVideosResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface YouTubeApi {
    @Headers("Accept: application/json")
    @GET("search")
    suspend fun searchVideos(
        @Query("key") key: String,
        @Query("channelId") channelId: String,
        @QueryMap(encoded = true) parameters: Map<String, String>
    ): GetYouTubeVideosResponse
}