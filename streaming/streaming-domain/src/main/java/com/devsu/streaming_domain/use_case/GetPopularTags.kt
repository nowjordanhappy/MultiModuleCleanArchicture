package com.devsu.streaming_domain.use_case

import com.devsu.streaming_domain.model.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPopularTags {
    fun execute(
    ): Flow<List<Tag>> = flow{
        emit(listOf("Jazz", "Pop", "Rock", "Funk","Gaming"))
    }
}