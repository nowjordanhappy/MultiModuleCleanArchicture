package com.devsu.streaming_domain.use_case

import com.devsu.streaming_domain.model.User
import com.devsu.streaming_domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCurrentUser(
    private val repository: UserRepository
){
    fun execute(
    ): Flow<User?> = flow {
        emit(repository.getCurrentUser())
    }
}