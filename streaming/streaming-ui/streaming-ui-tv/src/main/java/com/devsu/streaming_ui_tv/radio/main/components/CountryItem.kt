package com.devsu.streaming_ui_tv.radio.main.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.devsu.streaming_domain.model.Country
import com.murgupluoglu.flagkit.FlagKit

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CountryItem(
    modifier: Modifier = Modifier,
    country: Country,
    isSelected: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    onClick: () -> Unit,
    onChangeItem: (Country) -> Unit,
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
                    onChangeItem(country)
                }
            },
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