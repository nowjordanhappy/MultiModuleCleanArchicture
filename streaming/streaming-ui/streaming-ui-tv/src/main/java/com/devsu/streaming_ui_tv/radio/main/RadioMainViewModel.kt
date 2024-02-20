package com.devsu.streaming_ui_tv.radio.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsu.navigation.NavigationCommandSegment
import com.devsu.navigation.NavigationManager
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_domain.model.SearchRadioListParam
import com.devsu.streaming_domain.use_case.GetCurrentUser
import com.devsu.streaming_domain.use_case.GetPopularCountries
import com.devsu.streaming_domain.use_case.GetPopularTags
import com.devsu.streaming_domain.use_case.GetPopularYouTubeChannels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RadioMainViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val getCurrentUser: GetCurrentUser,
    private val getPopularTags: GetPopularTags,
    private val getPopularCountries: GetPopularCountries,
    private val getPopularYouTubeChannels: GetPopularYouTubeChannels,
): ViewModel() {
    var state by mutableStateOf(RadioMainState())

    init {
        onEvent(RadioMainEvent.OnGetCurrentUser)
        onEvent(RadioMainEvent.OnGetPopularTags)
        onEvent(RadioMainEvent.OnGetPopularCountries)
        onEvent(RadioMainEvent.OnGetPopularYouTubeChannels)
    }

    fun onEvent(event: RadioMainEvent){
        when(event){
            RadioMainEvent.OnGetCurrentUser -> onGetCurrentUser()
            RadioMainEvent.OnGetPopularTags -> onGetPopularTags()
            RadioMainEvent.OnGetPopularCountries -> onGetPopularCountries()
            RadioMainEvent.OnGetPopularYouTubeChannels -> onGetPopularYouTubeChannels()
            is RadioMainEvent.OnNavigateToRadioListByTag -> onNavigateToRadioListByTag(event)
            is RadioMainEvent.OnNavigateToRadioListByCountry -> onNavigateToRadioListByCountry(event)
            is RadioMainEvent.OnChangeYouTubeChannel -> onChangeChannelYouTube(event)
            is RadioMainEvent.OnNavigateToYouTubeVideo -> onNavigateToYouTubeVideo(event)
            is RadioMainEvent.OnChangeCountry -> onChangeCountry(event)
            is RadioMainEvent.OnChangePopularTag -> onChangePopularTag(event)
        }
    }

    private fun onGetPopularYouTubeChannels() {
        getPopularYouTubeChannels
            .execute()
            .onEach { youtubeChannels ->
                state = state.copy(
                    popularYouTubeChannels = youtubeChannels
                )
            }
            .launchIn(viewModelScope)
    }

    private fun onNavigateToRadioListByCountry(event: RadioMainEvent.OnNavigateToRadioListByCountry) {
        state = state.copy(
            youtubeChannelSelected = null,
            popularTagSelected = null
        )
        navigationManager.navigate(
            NavigationCommandSegment.DefaultNavigation(
                StreamingDirections.searchRadio(
                    key = SearchRadioListParam.CountryCode.param,
                    value = event.country
                )
            )
        )
    }

    private fun onNavigateToRadioListByTag(event: RadioMainEvent.OnNavigateToRadioListByTag) {
        state = state.copy(
            youtubeChannelSelected = null,
            countrySelected = null
        )
        navigationManager.navigate(
            NavigationCommandSegment.DefaultNavigation(
                StreamingDirections.searchRadio(
                    key = SearchRadioListParam.TagList.param,
                    value = event.tag
                )
            )
        )
    }

    private fun onNavigateToYouTubeVideo(event: RadioMainEvent.OnNavigateToYouTubeVideo) {
        if(event.youTubeChannel.channelId.isBlank())return
        state = state.copy(
            countrySelected = null,
            popularTagSelected = null
        )
        navigationManager.navigate(
            NavigationCommandSegment.DefaultNavigation(
                StreamingDirections.youTubeVideo(
                    channelId = event.youTubeChannel.channelId,
                    channelName = event.youTubeChannel.name
                )
            )
        )
    }

    private fun onChangeChannelYouTube(event: RadioMainEvent.OnChangeYouTubeChannel) {
        state = state.copy(
            youtubeChannelSelected = event.youTubeChannel
        )
    }

    private fun onGetPopularTags() {
        getPopularTags
            .execute()
            .onEach { tags ->
                state = state.copy(
                    popularTags = tags
                )
            }
            .launchIn(viewModelScope)
    }

    private fun onGetPopularCountries(){
        getPopularCountries
            .execute()
            .onEach { countries ->
                state = state.copy(
                    popularCountries = countries
                )
            }
            .launchIn(viewModelScope)
    }

    private fun onGetCurrentUser() {
        getCurrentUser
            .execute()
            .onEach { user ->
                state = state.copy(
                    userSelected = user
                )
            }
            .launchIn(viewModelScope)
    }

    private fun onChangePopularTag(event: RadioMainEvent.OnChangePopularTag) {
        state = state.copy(
            popularTagSelected = event.tag
        )
    }

    private fun onChangeCountry(event: RadioMainEvent.OnChangeCountry) {
        state = state.copy(
            countrySelected = event.country
        )
    }
}