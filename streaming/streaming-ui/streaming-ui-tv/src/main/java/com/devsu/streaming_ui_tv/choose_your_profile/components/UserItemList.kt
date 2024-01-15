package com.devsu.streaming_ui_tv.choose_your_profile.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import com.devsu.streaming_domain.model.User

@Composable
fun UserItemList(
    modifier: Modifier,
    users: List<User>,
    onSelectItem: (User) -> Unit,
    onChangeItem: (User) -> Unit,
) {
    TvLazyRow(
        modifier = modifier
    ) {
        items(
            users,
            { user -> user.id }
        ) { user ->
            UserItem(
                user = user, onSelectItem = onSelectItem, onChangeItem = onChangeItem
            )
        }
    }
}


