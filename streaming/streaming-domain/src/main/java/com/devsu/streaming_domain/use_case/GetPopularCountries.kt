package com.devsu.streaming_domain.use_case

import com.devsu.streaming_domain.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPopularCountries {
    fun execute(
    ): Flow<List<Country>> = flow{
        emit(listOf("PE","EC","US", "GB", "ES", "MX", "AR"))
    }
}