@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.devsu.streaming_ui_tv.youtube.youtube_video.components

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.devsu.streaming_domain.model.YouTubeVideo
import com.devsu.streaming_ui_tv.theme.DarkPurple

@Composable
fun YouTubeVideoItem(
    modifier: Modifier = Modifier,
    video: YouTubeVideo,
    focusRequester: FocusRequester = FocusRequester(),
    isSelected: Boolean = false,
    onClick: (YouTubeVideo)->Unit,
    onChangeItem: (YouTubeVideo) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            focusRequester.requestFocus()
        }
    }

    Card(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
                if (isFocused) {
                    onChangeItem(video)
                }
            },
        onClick = { onClick(video) },
        scale = CardDefaults.scale(focusedScale = 1.15f),
    ) {
        val context = LocalContext.current
        Column {
            Log.v("JordanRA", "name: ${video.title}- image: ${video.thumbnail}")
            YouTubeVideoImage(video.thumbnail?.let { Uri.parse(it) } ?: kotlin.run { null })
            Column (
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                if (video.datePublished.isNotBlank()) Text(
                    text = video.datePublished, maxLines = 1, overflow = TextOverflow.Ellipsis,
                    style = TextStyle.Default.copy(
                        color = Color.DarkGray
                    )
                )
                Text(
                    text = video.title, maxLines = 2, overflow = TextOverflow.Ellipsis,
                    style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                if (!video.description.isNullOrBlank()) Text(
                    text = video.description, maxLines = 3, overflow = TextOverflow.Ellipsis,
                    style = TextStyle.Default.copy(
                        color = Color.DarkGray
                    )
                )
            }
        }
    }
}

@Composable
fun YouTubeVideoImage(
    image: Uri?,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(0.dp))
            .aspectRatio(1.45f),
    ) {
        if(image != null) {
            var isError by remember {
                mutableStateOf(false)
            }
            if(!isError)AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                onError = {
                    isError = true
                    Log.v("JordanRA", "error: ${it.result.throwable}")
                },
            )
            if(isError) RadioDefaultImage()
        } else {
            RadioDefaultImage()
        }
    }
}

@Composable
fun RadioDefaultImage() {
    Icon(
        modifier = Modifier.fillMaxSize(),
        imageVector = Icons.Default.PlayCircleOutline, contentDescription = null,
        tint = DarkPurple.copy(alpha = 0.7f)
    )
}