package com.devsu.streaming_ui_tv.radio.tags.components

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.devsu.streaming_domain.model.Tag
import com.devsu.streaming_ui_tv.theme.DarkBlueTag

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TagItem(
    modifier: Modifier = Modifier,
    tag: Tag,
    isSelected: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    onClick: () -> Unit,
    onChangeItem: (Tag) -> Unit,
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
                    onChangeItem(tag)
                }
            },
        scale = CardDefaults.scale(focusedScale = 1.2f),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlueTag),
            contentAlignment = Alignment.Center
        ) {
            Text(
                tag,
                style = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
        }
    }
}