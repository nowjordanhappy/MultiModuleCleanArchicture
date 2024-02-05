@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.devsu.streaming_ui_tv.radio.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Button
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Tab
import androidx.tv.material3.TabColors
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text
import com.devsu.streaming_ui_tv.components.TvContainer
import com.devsu.streaming_ui_tv.radio.main.components.CountryItem
import com.devsu.streaming_ui_tv.radio.tags.components.TagItem

@Composable
fun RadioMainScreen(
    viewModel: RadioMainViewModel = hiltViewModel()
) {
    val state = viewModel.state

    TvContainer(
        modifier = Modifier,
        color = state.userSelected?.backgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Section(
                title = "Popular Countries",
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 20.dp)
            )

            TvLazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                items(state.popularCountries){ country->
                    CountryItem(
                        country = country,
                        modifier = Modifier
                            .padding(15.dp)
                            .size(120.dp, 75.dp),
                        onClick = {
                            viewModel.onEvent(RadioMainEvent.OnNavigateToRadioListByCountry(country))
                        }
                    )
                }
            }

            Section(
                title = "Popular Tags",
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 20.dp)
            )

            TvLazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                items(state.popularTags){ tag->
                    TagItem(
                        tag = tag,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp),
                        onClick = {
                            viewModel.onEvent(RadioMainEvent.OnNavigateToRadioListByTag(tag))
                        }
                    )
                }
            }
        }
    }

    /*val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    var tabIndex by remember { mutableStateOf(0) }

    Column {
        Button(onClick = {
            Log.v("JordanRA","changing to: ${tabIndex + 1 < titles.size}")
            if (tabIndex + 1 < titles.size){
                tabIndex += 1
            }else{
                tabIndex = 0
            }
            Log.v("JordanRA","changing to: $tabIndex")
        }) {
            Text("change nex index")
        }
        TabRow(selectedTabIndex = tabIndex) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        Log.v("JordanRA","onClick: $index")
                              //tabIndex = index
                              },
                    onFocus = {
                        tabIndex = index
                        Log.v("JordanRA","onFocus: $index")
                    },
                    enabled = true,
                    modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp),
                    colors = TabDefaults.pillIndicatorTabColors(
                        selectedContentColor = Color.DarkGray.copy(alpha = 0.5f),
                    )
                ) {
                    Text(title, style = TextStyle.Default.copy(
                        fontSize = 30.sp
                    ))
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Selected page: ${titles[tabIndex]}", style = TextStyle.Default.copy(
                color = Color.White
            ))
        }
    }*/
}

@Composable
fun Section(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        title,
        modifier = modifier,
        style = TextStyle.Default.copy(
            color = Color.White,
            fontSize = 30.sp
        )
    )
}