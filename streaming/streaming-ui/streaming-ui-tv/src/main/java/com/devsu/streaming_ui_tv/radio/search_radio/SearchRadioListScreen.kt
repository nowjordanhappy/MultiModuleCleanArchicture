package com.devsu.streaming_ui_tv.radio.search_radio

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_ui_tv.R
import com.devsu.streaming_ui_tv.components.LoadingView
import com.devsu.streaming_ui_tv.components.TvContainer
import com.devsu.streaming_ui_tv.radio.components.RadioList
import com.devsu.streaming_ui_tv.radio.main.components.Section
import com.devsu.streaming_ui_tv.youtube.youtube_video.YouTubeVideoUiEvent

@Composable
fun SearchRadioListScreen(
    scaffoldState: SnackbarHostState,
    viewModel: SearchRadioListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event->
            when(event){
                is SearchRadioListUiEvent.ShowError -> {
                    scaffoldState.showSnackbar(event.message)
                }
            }
        }
    }

    TvContainer(
        modifier = Modifier,
        color = Color.Blue
    ) {

        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Section(
                title = state.title ?: stringResource(R.string.radio_list),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .padding(top = 20.dp)
            )

            if(state.progressBarState == ProgressBarState.Idle){
                RadioList(
                    modifier = Modifier.fillMaxSize(),
                    items = state.radioList,
                    radioSelected = state.radioSelected,
                    onSelectItem = { radio ->
                        viewModel.onEvent(SearchRadioListEvent.OnNavigateToToRadioPlayer(radio))
                    },
                    onChangeItem = { radio ->
                        viewModel.onEvent(SearchRadioListEvent.OnChangeRadio(radio))
                    }
                )
            }else{
                LoadingView()
            }
        }
    }
}