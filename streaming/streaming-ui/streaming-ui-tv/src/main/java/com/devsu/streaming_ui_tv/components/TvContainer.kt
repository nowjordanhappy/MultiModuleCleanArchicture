@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.devsu.streaming_ui_tv.components

import androidx.annotation.ColorInt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.devsu.streaming_ui_tv.theme.BackgroundDefault
import com.devsu.streaming_ui_tv.util.conditional
import com.devsu.streaming_ui_tv.util.getBackgroundGradient

@Composable
fun TvContainer(
    color: Color? = null,
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .conditional(color != null, ifTrue = {
                background(getBackgroundGradient(color!!))
            }, ifFalse = {
                background(BackgroundDefault)
            })
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun TvContainer(
    @ColorInt color: Int? = null,
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val newColor: Color? = color?.let {
        Color(it)
    } ?: run { null }
    TvContainer(
        color = newColor,
        modifier = modifier,
        content = content
    )
}