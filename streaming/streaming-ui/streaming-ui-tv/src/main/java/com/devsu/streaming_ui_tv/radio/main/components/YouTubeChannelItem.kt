package com.devsu.streaming_ui_tv.radio.main.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.devsu.streaming_domain.model.YouTubeChannel

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun YouTubeChannelItem(
    modifier: Modifier = Modifier,
    channel: YouTubeChannel,
    isSelected: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    onClick: () -> Unit,
    onChangeItem: (YouTubeChannel) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(isSelected){
        if(isSelected) {
            focusRequester.requestFocus()
        }
    }

    Card(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
                if (isFocused) {
                    onChangeItem(channel)
                }
                Log.v("TV", "here ${channel.name}- hasFocus: ${isFocused}")
            },
        scale = CardDefaults.scale(focusedScale = 1.2f),
        onClick = onClick
    ) {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text(channel.name, style = TextStyle.Default.copy(
                color = Color.White,
                fontSize = 20.sp
            ))

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(channel.logo)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                onError = {
                    Log.v("JordanRA", "error: ${it.result.throwable}")
                },
            )
        }
    }
}