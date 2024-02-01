package com.devsu.streaming_ui_tv.radio.radio_by_tag

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_ui_tv.components.TvContainer
import com.devsu.streaming_ui_tv.radio.components.RadioList

@Composable
fun RadioByTagScreen(
    scaffoldState: SnackbarHostState,
    viewModel: RadioByTagViewModel = hiltViewModel()
) {
    val state = viewModel.state

    TvContainer(
        modifier = Modifier,
        color = Color.Blue
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){

            RadioList(
                modifier = Modifier.fillMaxSize(),
                items = state.radioList,
                onSelectItem = { radio ->
                    Log.v("JordanRA", "StreamingDirections.radioPlayer().route: ${StreamingDirections.radioPlayer().route}")
                    Log.v("TV", "RadioList onClick: ${radio.name}")
                    viewModel.onEvent(RadioByTagEvent.OnNavigateToToRadioPlayer(radio))
                }
            )

            /*UserItemList(
                modifier = Modifier,
                users = state.users,
                onChangeItem = { user ->
                    viewModel.onEvent(ChooseYourProfileEvent.OnChangeUser(user))
                    Log.v("TV", "user focused: ${user.name}")
                },
                onSelectItem = { user ->
                    viewModel.onEvent(ChooseYourProfileEvent.OnSelectUser(user))
                    Log.v("TV", "user selected: ${user.name}")
                }
            )*/
        }
    }
}