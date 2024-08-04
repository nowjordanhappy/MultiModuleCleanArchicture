package com.devsu.streaming_ui_tv.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    pages: Int,
    currentPage: Int?
) {
    Row(
        Modifier
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pages) { iteration ->
            val color = if (currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(color)
                    .height(4.dp)
                    .border(width = 0.5.dp, color = Color.White.copy(alpha = 0.8f), shape = RoundedCornerShape(2.dp))
                    .width(22.dp)
            )
        }
    }
}