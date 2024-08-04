package com.devsu.streaming_domain.use_case

import com.devsu.streaming_domain.repository.UserRepository
import kotlinx.coroutines.flow.flow

class SetCurrentUser(
    private val repository: UserRepository
){
    fun execute(
        id: Int
    ) = flow {
        repository.setCurrentUser(id)
        emit(Unit)
    }
}