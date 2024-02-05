package com.devsu.streaming_ui_tv.radio.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.devsu.streaming_domain.model.Country
import com.devsu.streaming_domain.model.Tag
import com.devsu.streaming_ui_tv.theme.DarkBlueTag
import com.murgupluoglu.flagkit.FlagKit

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CountryItem(
    modifier: Modifier = Modifier,
    country: Country,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        scale = CardDefaults.scale(focusedScale = 1.2f),
        onClick = onClick
    ) {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            println("country: $country")
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(
                    id = FlagKit.getResId(
                        context,
                        country
                    )
                ), contentDescription = country,
                contentScale = ContentScale.Crop
            )
        }
    }
}