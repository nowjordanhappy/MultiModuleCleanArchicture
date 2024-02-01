package com.devsu.streaming_domain.use_case

import android.util.Log
import com.devsu.streaming_domain.model.User
import com.devsu.streaming_domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SetCurrentUser(
    private val repository: UserRepository
){
    fun execute(
        id: Int
    ) = flow {
        Log.v("JordanRA", "execute use case")
        repository.setCurrentUser(id)
        emit(Unit)
    }
}