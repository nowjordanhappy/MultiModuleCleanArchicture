package com.devsu.streaming_ui_tv.youtube_video.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import com.devsu.streaming_domain.model.YouTubeVideo

@Composable
fun YouTubeVideoList(
    modifier: Modifier = Modifier,
    items: List<YouTubeVideo>,
    videoSelected: YouTubeVideo? = null,
    onSelectItem: (YouTubeVideo) -> Unit,
    onChangeItem: (YouTubeVideo) -> Unit
) {
    TvLazyVerticalGrid(
        columns = TvGridCells.Fixed(3),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp)
    ) {
        items(items = items, key = { it.videoId }) { video ->
            YouTubeVideoItem(
                modifier = Modifier
                    .padding(10.dp)
                    .height(300.dp),
                isSelected = (video.videoId == videoSelected?.videoId),
                video = video,
                onClick = {
                    onSelectItem.invoke(video)
                },
                onChangeItem = onChangeItem
            )
        }
    }
}