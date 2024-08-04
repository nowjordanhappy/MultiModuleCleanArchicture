package com.devsu.streaming_domain.use_case

import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.core_ui.model.UIComponent
import com.devsu.streaming_domain.model.User
import com.devsu.streaming_domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsers(
    private val repository: UserRepository
) {
    fun execute(
    ): Flow<List<User>> = flow {
        emit(repository.getUsers())
    }
}