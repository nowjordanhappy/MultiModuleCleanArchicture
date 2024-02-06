package com.devsu.streaming_ui_tv.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text

@Composable
fun TitlePage(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        title,
        modifier = modifier,
        style = TextStyle.Default.copy(
            color = Color.White,
            fontSize = 50.sp
        )
    )
}