package com.devsu.streaming_ui_tv.choose_your_profile

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.UIComponent
import com.devsu.navigation.NavigationCommandSegment
import com.devsu.navigation.NavigationManager
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_domain.use_case.GetCurrentUser
import com.devsu.streaming_domain.use_case.GetUsers
import com.devsu.streaming_domain.use_case.SetCurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class ChooseYourProfileViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val getUsers: GetUsers,
    private val getCurrentUser: GetCurrentUser,
    private val setCurrentUser: SetCurrentUser

): ViewModel(){
    var state by mutableStateOf(ChooseYourProfileState())

    init {
        onEvent(ChooseYourProfileEvent.OnGetUsers)
    }

    fun onEvent(event: ChooseYourProfileEvent){
        when(event){
            ChooseYourProfileEvent.OnGetUsers -> onGetUsers()
            is ChooseYourProfileEvent.OnChangeUser -> onChangeUser(event)
            is ChooseYourProfileEvent.OnSelectUser -> onSelectUser(event)
            ChooseYourProfileEvent.OnNavigateToMain -> onNavigateToMain()
            ChooseYourProfileEvent.OnGetCurrentUser -> onGetCurrentUser()
        }
    }

    private fun onGetCurrentUser() {
        Log.v("JordanRA", "onGetCurrentUser")
        getCurrentUser
            .execute()
            .onEach { user ->
                Log.v("JordanRA", "onGetCurrentUser: ${user}")
                state = state.copy(
                    userSelected = user
                )
            }
            .launchIn(viewModelScope)
    }

    private fun onSelectUser(event: ChooseYourProfileEvent.OnSelectUser) {
        Log.v("JordanRA", "onSelectUser: ${event.user.id}")
        setCurrentUser
            .execute(
                event.user.id
            )
            .onEach { _ ->
                Log.v("JordanRA", "onNavigateToMain")
                onNavigateToMain()
            }
            .launchIn(viewModelScope)
    }

    private fun onNavigateToMain() {
        navigationManager.navigate(
            NavigationCommandSegment.BuilderNavigation(
                StreamingDirections.RadioMain
            ){
                popUpTo(StreamingDirections.ChooseYourProfile.route){
                    inclusive = true
                }
            }
        )
    }

    private fun onGetUsers() {
        Log.v("JordanRA", "onGetUsers")
        getUsers
            .execute(
            )
            .onEach { users ->
                state = state.copy(
                    users = users
                )
                onEvent(ChooseYourProfileEvent.OnGetCurrentUser)
            }
            .launchIn(viewModelScope)
    }

    private fun onChangeUser(event: ChooseYourProfileEvent.OnChangeUser) {
        state = state.copy(
            userSelected = event.user
        )
    }
}