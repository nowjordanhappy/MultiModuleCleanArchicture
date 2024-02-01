package com.devsu.streaming_ui_tv.choose_your_profile

import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_domain.model.User
import com.devsu.streaming_ui_tv.radio.radio_by_tag.RadioByTagEvent

sealed interface ChooseYourProfileEvent{
    object OnGetUsers: ChooseYourProfileEvent
    class OnChangeUser(val user: User?): ChooseYourProfileEvent
    class OnSelectUser(val user: User): ChooseYourProfileEvent
    object OnGetCurrentUser: ChooseYourProfileEvent
    object OnNavigateToMain: ChooseYourProfileEvent
}