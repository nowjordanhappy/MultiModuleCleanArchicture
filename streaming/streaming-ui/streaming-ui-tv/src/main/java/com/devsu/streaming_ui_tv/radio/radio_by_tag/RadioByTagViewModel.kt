package com.devsu.streaming_ui_tv.radio.radio_by_tag

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.UIComponent
import com.devsu.core_ui.utils.ManagerConnection
import com.devsu.navigation.NavigationCommand
import com.devsu.navigation.NavigationCommandSegment
import com.devsu.navigation.NavigationManager
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_domain.use_case.GetRadioListByTag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import java.nio.charset.StandardCharsets
import javax.inject.Inject

const val ARG_TAG = "ARG_TAG"

@HiltViewModel
class RadioByTagViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val savedStateHandle: SavedStateHandle,
    private val getRadioListByTag: GetRadioListByTag,
    private val managerConnection: ManagerConnection
): ViewModel() {
    var state by mutableStateOf(RadioByTagState())

    private val _uiEvent = Channel<RadioListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        if(savedStateHandle.contains(ARG_TAG)){
            savedStateHandle.get<String>(ARG_TAG)?.let { tag ->
                onEvent(RadioByTagEvent.OnGetRadioList(tag = tag))
            }
        }else{
            onEvent(RadioByTagEvent.OnGetRadioList(tag = "#90"))
        }
    }

    fun onEvent(event: RadioByTagEvent){
        when(event){
            is RadioByTagEvent.OnGetRadioList -> onGetRadioList(event)
            is RadioByTagEvent.OnNavigateToToRadioPlayer -> onNavigateToToRadioPlayer(event)
        }
    }

    private fun onNavigateToToRadioPlayer(event: RadioByTagEvent.OnNavigateToToRadioPlayer) {
        val command = StreamingDirections.radioPlayer(
            stationuuid = event.radio.stationuuid,
            name = event.radio.name,
            favicon = Uri.encode(event.radio.favicon, StandardCharsets.UTF_8.toString()),
            urlResolved = Uri.encode(event.radio.urlResolved, StandardCharsets.UTF_8.toString()),
            tagList = event.radio.tags
        )
        Log.v("JordanRA", "StreamingDirections.radioPlayer().destimation: ${command.destination}")
        navigationManager.navigate(
            NavigationCommandSegment.DefaultNavigation(command)
        )
    }

    private fun onGetRadioList(event: RadioByTagEvent.OnGetRadioList) {
        getRadioListByTag
            .execute(
                tag = event.tag,
                limit = state.limit,
                page = state.page,
                isNetworkAvailable = managerConnection.isNetworkAvailable()
            )
            .onEach { dataState->
                when(dataState){
                    is DataState.Data -> {
                        state = state.copy(
                            radioList = dataState.data ?: emptyList()
                        )
                    }
                    is DataState.Loading -> {
                        state = state.copy(
                            progressBarState = dataState.progressBarState
                        )
                    }
                    is DataState.Response -> {
                        when(dataState.uiComponent){
                            is UIComponent.None -> {
                                val message = (dataState.uiComponent as UIComponent.None).message
                                _uiEvent.send(RadioListUiEvent.ShowError(message))
                            }
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}

sealed class RadioListUiEvent{
    object SuccessSearch: RadioListUiEvent()
    data class ShowError(val message: String): RadioListUiEvent()
}