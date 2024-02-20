package com.devsu.streaming_ui_tv.radio.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text

@Composable
fun Section(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        title,
        modifier = modifier,
        style = TextStyle.Default.copy(
            color = Color.White,
            fontSize = 30.sp,
            shadow = Shadow(
                color = Color.DarkGray.copy(alpha = 0.6f),
                offset = Offset(-2.0f, -1.0f),
                blurRadius = 2f
            )
        )
    )
}