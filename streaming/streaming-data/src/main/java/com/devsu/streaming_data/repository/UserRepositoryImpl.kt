package com.devsu.streaming_data.repository

import android.graphics.Color
import com.devsu.streaming_domain.model.User
import com.devsu.streaming_domain.repository.UserRepository

class UserRepositoryImpl(
) : UserRepository {
    private val users = getTestUsers()

    override suspend fun getUsers(): List<User> {
        return users
    }

    private fun getTestUsers(): List<User> {
        val user1 = User(
            id = 1,
            name = "Jordan",
            avatar = "https://img.freepik.com/free-psd/3d-illustration-human-avatar-profile_23-2150671116.jpg",
            backgroundColor = Color.parseColor("#3b3b3b")
        )
        val user2 = User(
            id = 2,
            name = "Ari",
            avatar = "https://img.freepik.com/free-psd/3d-illustration-person-with-long-hair_23-2149436197.jpg",
            backgroundColor = Color.parseColor("#301934")
        )
        val user3 = User(
            id = 3,
            name = "Dad",
            avatar = "https://img.freepik.com/free-psd/3d-illustration-business-man-with-glasses_23-2149436194.jpg",
            backgroundColor = Color.parseColor("#0C2D48")
        )
        return listOf(user1, user2, user3)
    }
}