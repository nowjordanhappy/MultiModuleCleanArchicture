package com.devsu.streaming_domain.repository

import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_domain.model.User

interface RadioRepository {
    suspend fun getRadios(): List<Radio>
    suspend fun getRadioListByTag(
        tag: String,
        limit: Int,
        order: String,
        reverse: Boolean,
        page: Int
    ): List<Radio>
}