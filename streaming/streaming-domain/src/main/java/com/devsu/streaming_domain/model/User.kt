package com.devsu.streaming_domain.model

import androidx.annotation.ColorInt

data class User(
    val id: Int,
    val name: String,
    val avatar: String,
    @ColorInt val backgroundColor: Int
)