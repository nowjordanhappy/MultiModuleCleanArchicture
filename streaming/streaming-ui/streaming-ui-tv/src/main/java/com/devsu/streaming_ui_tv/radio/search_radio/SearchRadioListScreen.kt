package com.devsu.streaming_ui_tv.radio.search_radio

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_ui_tv.components.LoadingView
import com.devsu.streaming_ui_tv.components.TvContainer
import com.devsu.streaming_ui_tv.radio.components.RadioList
import com.devsu.streaming_ui_tv.radio.main.components.Section

@Composable
fun SearchRadioListScreen(
    scaffoldState: SnackbarHostState,
    viewModel: SearchRadioListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    TvContainer(
        modifier = Modifier,
        color = Color.Blue
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Section(
                title = state.title ?: "Radio List",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .padding(top = 20.dp)
            )

            if(state.progressBarState == ProgressBarState.Idle){
                RadioList(
                    modifier = Modifier.fillMaxSize(),
                    items = state.radioList,
                    onSelectItem = { radio ->
                        Log.v("JordanRA", "StreamingDirections.radioPlayer().route: ${StreamingDirections.radioPlayer().route}")
                        Log.v("TV", "RadioList onClick: ${radio.name}")
                        viewModel.onEvent(SearchRadioListEvent.OnNavigateToToRadioPlayer(radio))
                    }
                )
            }else{
                LoadingView()
            }
        }
    }
}