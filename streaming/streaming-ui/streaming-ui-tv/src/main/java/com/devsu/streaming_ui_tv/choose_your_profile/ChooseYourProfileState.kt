package com.devsu.streaming_ui_tv.choose_your_profile

import com.devsu.streaming_domain.model.User

data class ChooseYourProfileState(
    val users: List<User> = emptyList(),
    val userSelected: User? = null,
)
