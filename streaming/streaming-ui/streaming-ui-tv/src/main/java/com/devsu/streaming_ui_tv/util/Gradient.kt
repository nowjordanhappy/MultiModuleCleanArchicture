package com.devsu.streaming_ui_tv.util

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

fun getBackgroundGradient(color: Color): Brush {
    return Brush.radialGradient(
        0.0f to color.copy(alpha = 0.2f),
        0.5f to color.copy(alpha = 0.3f),
        0.7f to color.copy(alpha = 0.4f),
        0.9f to color.copy(alpha = 0.5f),
        1.0f to color,
        radius = 2000.0f,
        tileMode = TileMode.Repeated
    )
}