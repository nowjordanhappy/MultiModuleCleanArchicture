package com.devsu.streaming_ui_tv.choose_your_profile

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.Text
import com.devsu.core.toHexColor
import com.devsu.streaming_domain.model.User
import com.devsu.streaming_ui_tv.R
import com.devsu.streaming_ui_tv.choose_your_profile.components.UserItemList
import com.devsu.streaming_ui_tv.components.TvContainer
import com.devsu.streaming_ui_tv.theme.DarkPurple

@Composable
fun ChooseYourProfile(
    scaffoldState: SnackbarHostState,
    viewModel: ChooseYourProfileViewModel = hiltViewModel()
) {
    val state = viewModel.state

    TvContainer(
        modifier = Modifier,
        color = state.userSelected?.backgroundColor
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                modifier = Modifier,
                text = stringResource(R.string.who_s_watching),
                style = TextStyle.Default.copy(
                    fontSize = 36.sp,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier
                .height(35.dp))

            UserItemList(
                modifier = Modifier,
                users = state.users,
                onChangeItem = { user ->
                    viewModel.onEvent(ChooseYourProfileEvent.OnChangeUser(user))
                    Log.v("TV", "user focused: ${user.name}")
                },
                onSelectItem = { user ->
                    Log.v("TV", "user selected: ${user.name} - color: ${user.backgroundColor.toHexColor()}")
                    viewModel.onEvent(ChooseYourProfileEvent.OnSelectUser(user))
                }
            )

            Spacer(modifier = Modifier
                .height(25.dp))

            state.userSelected?.let {  userSelected->
                Text(
                    modifier = Modifier,
                    text = userSelected.name,
                    style = TextStyle.Default.copy(
                        fontSize = 25.sp,
                        color = Color.White
                    )
                )
            } ?: kotlin.run {
                Text(
                    modifier = Modifier,
                    text = "Select a user",
                    style = TextStyle.Default.copy(
                        fontSize = 25.sp,
                        color = Color.DarkGray.copy()
                    )
                )
            }
        }
    }
}

fun getTestUsers(): List<User> {
    val user1 = User(
        id = 1,
        name = "Jordan",
        avatar = "https://img.freepik.com/free-psd/3d-illustration-human-avatar-profile_23-2150671116.jpg",
        backgroundColor = Color.DarkGray.toArgb()
    )
    val user2 = User(
        id = 2,
        name = "Ari",
        avatar = "https://img.freepik.com/free-psd/3d-illustration-person-with-long-hair_23-2149436197.jpg",
        backgroundColor = DarkPurple.toArgb()
    )
    val user3 = User(
        id = 3,
        name = "Dad",
        avatar = "https://img.freepik.com/free-psd/3d-illustration-business-man-with-glasses_23-2149436194.jpg",
        backgroundColor = DarkPurple.toArgb()
    )
    return listOf(user1, user2, user3)
}