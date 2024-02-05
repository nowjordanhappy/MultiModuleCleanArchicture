package com.devsu.streaming_data.repository

import android.util.Log
import com.devsu.streaming_data.mapper.RadioDtoMapper
import com.devsu.streaming_data.remote.RadioApi
import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_domain.repository.RadioRepository
import kotlin.math.max

class RadioRepositoryImpl(
    private val service: RadioApi,
    private val mapper: RadioDtoMapper
): RadioRepository {
    override suspend fun getRadios(): List<Radio> {
        return emptyList()
    }

    override suspend fun searchRadioList(
        params: Map<String, String>,
        limit: Int,
        order: String,
        reverse: Boolean,
        page: Int
    ): List<Radio> {
        Log.v("JordanRA", "searchRadioList: ${params}")
        val response = service.searchRadioList(
            limit = limit,
            order = order,
            parameters = params,
            reverse = reverse,
            offset = max(0, page - 1)
        )
        Log.v("JordanRA", "searchRadioList after: ${params}")

        return response.mapNotNull { mapper.mapToDomainModel(it) }
    }
}