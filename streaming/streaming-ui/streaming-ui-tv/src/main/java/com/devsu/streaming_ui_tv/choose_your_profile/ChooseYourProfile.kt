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
                userSelected = state.userSelected,
                onChangeItem = { user ->
                    viewModel.onEvent(ChooseYourProfileEvent.OnChangeUser(user))
                },
                onSelectItem = { user ->
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
                    text = stringResource(R.string.select_a_user),
                    style = TextStyle.Default.copy(
                        fontSize = 25.sp,
                        color = Color.DarkGray.copy()
                    )
                )
            }
        }
    }
}