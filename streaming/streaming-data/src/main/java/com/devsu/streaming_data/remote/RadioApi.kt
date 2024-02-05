package com.devsu.streaming_data.remote

import com.devsu.streaming_data.remote.dto.radio.GetRadioListResponse
import com.devsu.streaming_data.remote.dto.radio.GetTagRadioListResponse
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RadioApi {
    @Headers("Accept: application/json")
    @GET("json/tags")
    suspend fun getTagRadioList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("order") order: String,
    ): GetTagRadioListResponse

    @Headers("Accept: application/json")
    @GET("json/tags")
    suspend fun getRadioList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("order") order: String,
        @Query("reverse") reverse: Boolean,
        @Query("tagList") tagList: String,
    ): GetRadioListResponse

    @Headers("Accept: application/json")
    @GET("json/stations/search")
    suspend fun searchRadioList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("order") order: String,
        @Query("reverse") reverse: Boolean,
        @QueryMap(encoded = true) parameters: Map<String, String>
    ): GetRadioListResponse
}