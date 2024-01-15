package com.devsu.streaming_domain.repository

import com.devsu.streaming_domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}