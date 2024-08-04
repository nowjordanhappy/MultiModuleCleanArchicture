package com.devsu.streaming_domain.repository

import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_domain.model.SearchRadioListParam

interface RadioRepository {
    suspend fun getRadios(): List<Radio>
    suspend fun searchRadioList(
        params: Map<String, String>,
        limit: Int,
        order: String,
        reverse: Boolean,
        page: Int
    ): List<Radio>
}