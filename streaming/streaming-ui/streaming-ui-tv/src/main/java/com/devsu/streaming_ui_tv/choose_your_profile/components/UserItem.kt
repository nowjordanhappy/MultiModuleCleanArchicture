package com.devsu.streaming_ui_tv.choose_your_profile.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.devsu.streaming_domain.model.User
import com.devsu.streaming_ui_tv.util.conditional

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: User,
    onSelectItem: (User) -> Unit,
    onChangeItem: (User) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(10.dp)
    ) {
        //val interactionSource = remember { MutableInteractionSource() }
        var isFocused by remember { mutableStateOf(true) }

        Surface(
            modifier = modifier
                .padding(horizontal = 32.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                    if (isFocused) {
                        onChangeItem(user)
                    }
                    Log.v("TV", "here ${user.name}- hasFocus: ${isFocused}")
                }
                ,
            onClick = { onSelectItem.invoke(user) },
            border = ClickableSurfaceDefaults.border(
                border = Border(
                    border = BorderStroke(
                        4.dp,
                        Color.Transparent,
                    ),
                    shape = CircleShape,
                ),
                focusedBorder = Border(
                    border = BorderStroke(
                        4.dp,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    ),
                    shape = CircleShape,
                ),
            ),
            shape = ClickableSurfaceDefaults.shape(shape = CircleShape),
            scale = ClickableSurfaceDefaults.scale(focusedScale = 1.3f),
        ) {
            Box(
                modifier = Modifier
                    .conditional(isFocused, ifTrue = {
                        border(
                            4.dp,
                            Color.White,
                            CircleShape
                        )
                    })
                    .size(150.dp)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = (user.name.firstOrNull() ?: "U").toString().uppercase(),
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle.Default
                        .copy(
                            color = Color.White
                        )
                )

                AsyncImage(
                    model = user.avatar,
                    contentDescription = user.name,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}