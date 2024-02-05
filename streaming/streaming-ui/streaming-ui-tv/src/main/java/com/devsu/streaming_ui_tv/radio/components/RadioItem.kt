@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.devsu.streaming_ui_tv.radio.components

import android.net.Uri
import android.text.Layout
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_ui_tv.theme.DarkPurple
import com.murgupluoglu.flagkit.FlagKit

@Composable
fun RadioItem(
    modifier: Modifier = Modifier,
    radio: Radio,
    onClick: ()->Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        scale = CardDefaults.scale(focusedScale = 1.15f),
    ) {
        val context = LocalContext.current
        Column {
            Log.v("JordanRA", "name: ${radio.name}- image: ${radio.favicon}")
            RadioImage(radio.favicon?.let { Uri.parse(it) } ?: kotlin.run { null })
            Column (
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ){
                Text(text = radio.name, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "⭐${radio.votes}",
                        modifier = Modifier.weight(1f)
                    )
                    radio.countrycode?.let {  countrycode->
                        Log.v("JordanRA", "countrycode: $countrycode: -length: ${countrycode.length}")
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Image(
                                painter = painterResource(
                                    id = FlagKit.getResId(
                                        context,
                                        countrycode
                                    )
                                ), contentDescription = countrycode,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RadioImage(
    image: Uri?,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .aspectRatio(1f),
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
            if(isError)RadioDefaultImage()
        } else {
            RadioDefaultImage()
        }
    }
}

@Composable
fun RadioDefaultImage() {
    Icon(
        modifier = Modifier.fillMaxSize(),
        imageVector = Icons.Default.MusicNote, contentDescription = null,
        tint = DarkPurple.copy(alpha = 0.7f)
    )
}