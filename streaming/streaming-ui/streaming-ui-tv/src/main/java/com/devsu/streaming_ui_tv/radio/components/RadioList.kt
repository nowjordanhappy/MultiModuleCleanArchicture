package com.devsu.streaming_ui_tv.radio.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import com.devsu.streaming_domain.model.Radio

@Composable
fun RadioList(
    modifier: Modifier = Modifier,
    items: List<Radio>,
    onSelectItem: (Radio)->Unit
) {
    TvLazyVerticalGrid(
        columns = TvGridCells.Fixed(5),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp)
    ){
        items(items = items, key = {it.stationuuid}){ radio ->
            RadioItem(
                modifier = Modifier.padding(10.dp)
                    .height(230.dp),
                radio = radio, onClick = {
                onSelectItem.invoke(radio)
            })
        }
    }
}