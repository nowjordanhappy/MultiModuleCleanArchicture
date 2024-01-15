package com.devsu.streaming_ui_tv.choose_your_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.UIComponent
import com.devsu.streaming_domain.use_case.GetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChooseYourProfileViewModel @Inject constructor(
    private val getUsers: GetUsers

): ViewModel(){
    var state by mutableStateOf(ChooseYourProfileState())

    init {
        onEvent(ChooseYourProfileEvent.OnGetUsers)
    }

    fun onEvent(event: ChooseYourProfileEvent){
        when(event){
            ChooseYourProfileEvent.OnGetUsers -> onGetUsers()
            is ChooseYourProfileEvent.OnChangeUser -> onChangeUser(event)
            is ChooseYourProfileEvent.OnSelectUser -> Unit
        }
    }

    private fun onGetUsers() {
        getUsers
            .execute(
            )
            .onEach { users ->
                state = state.copy(
                    users = users
                )
            }
            .launchIn(viewModelScope)
    }

    private fun onChangeUser(event: ChooseYourProfileEvent.OnChangeUser) {
        state = state.copy(
            userSelected = event.user
        )
    }
}