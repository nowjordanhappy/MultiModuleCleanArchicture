@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.devsu.streaming_ui_tv.radio.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ImmersiveList
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.devsu.core_ui.R
import com.devsu.streaming_domain.model.YouTubeChannel
import com.devsu.streaming_ui_tv.components.PageIndicator
import com.devsu.streaming_ui_tv.components.TvContainer
import com.devsu.streaming_ui_tv.radio.main.components.CountryItem
import com.devsu.streaming_ui_tv.radio.main.components.Section
import com.devsu.streaming_ui_tv.radio.main.components.YouTubeChannelItem
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

        TvLazyColumn {
            item {
                val configuration = LocalConfiguration.current
                val screenHeight = configuration.screenHeightDp.dp

                val tvRowListState = rememberTvLazyListState()

                ImmersiveList(
                    modifier = Modifier.height(screenHeight * 0.8f),
                    background = { index, listHasFocus ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val channel = state.popularYouTubeChannels[index]
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(channel.background)
                                    .build(),
                                contentDescription = channel.name,
                                contentScale = ContentScale.FillHeight ,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.BottomEnd),
                                alignment = Alignment.BottomEnd
                            )

                            Image(
                                painter = painterResource(id = R.drawable.scrim),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                alignment = Alignment.BottomStart,
                                contentScale = ContentScale.FillWidth
                            )

                            ContentBlock(
                                index = channel,
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 60.dp, end = 30.dp, bottom = 30.dp)
                                    .fillMaxWidth(0.6f)
                            )
                        }
                    }) {

                    Column {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .padding(end = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            PageIndicator(
                                pages = state.popularYouTubeChannels.size,
                                currentPage = state.popularYouTubeChannels.indexOfFirst {  it == state.youtubeChannelSelected}
                            )
                        }

                        TvLazyRow(
                            pivotOffsets = PivotOffsets(0.5f, 0.5f),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            state = tvRowListState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            itemsIndexed(state.popularYouTubeChannels) { index, channel ->
                                YouTubeChannelItem(
                                    channel = channel,
                                    isSelected = (channel.channelId == state.youtubeChannelSelected?.channelId),
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .size(100.dp, 100.dp)
                                        .immersiveListItem(index),
                                    onChangeItem = {
                                        viewModel.onEvent(RadioMainEvent.OnChangeYouTubeChannel(it))
                                    },
                                    onClick = {
                                        viewModel.onEvent(RadioMainEvent.OnNavigateToYouTubeVideo(it))
                                    }
                                )
                            }
                        }
                    }
                }
            }

            item{
                Section(
                    title = stringResource(com.devsu.streaming_ui_tv.R.string.popular_countries),
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .padding(top = 20.dp)
                )

                TvLazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    items(state.popularCountries) { country ->
                        CountryItem(
                            country = country,
                            isSelected = (country == state.countrySelected),
                            modifier = Modifier
                                .padding(15.dp)
                                .size(120.dp, 75.dp),
                            onClick = {
                                viewModel.onEvent(
                                    RadioMainEvent.OnNavigateToRadioListByCountry(it)
                                )
                            },
                            onChangeItem = {
                                viewModel.onEvent(
                                    RadioMainEvent.OnChangeCountry(it)
                                )
                            }
                        )
                    }
                }
            }

            item {
                Section(
                    title = stringResource(com.devsu.streaming_ui_tv.R.string.popular_tags),
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .padding(top = 20.dp)
                )

                TvLazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    items(state.popularTags) { tag ->
                        TagItem(
                            tag = tag,
                            isSelected = (tag == state.popularTagSelected),
                            modifier = Modifier
                                .padding(10.dp)
                                .size(100.dp),
                            onClick = {
                                viewModel.onEvent(RadioMainEvent.OnNavigateToRadioListByTag(tag))
                            },
                            onChangeItem = {
                                viewModel.onEvent(
                                    RadioMainEvent.OnChangePopularTag(it)
                                )
                            }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(40.dp))
            }

        }
    }
}

@Composable
fun ContentBlock(index: YouTubeChannel, modifier: Modifier) {

    AnimatedContent(
        targetState = index,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 500)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = 500))
        },
        label = "",
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
    ) { item ->
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.name,
                style = TextStyle.Default.copy(
                    color = Color.White,
                    fontSize = 45.sp,
                ),
            )

            Text(
                text = item.title,
                style = TextStyle.Default.copy(
                    color = Color.White,
                    fontSize = 25.sp,
                ),
            )

            Text(
                text = item.subtitle,
                style = TextStyle.Default.copy(
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 17.sp,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}