@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.devsu.streaming_ui_tv.radio.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Tab
import androidx.tv.material3.TabColors
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text

@Composable
fun RadioMainScreen() {
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    var tabIndex by remember { mutableStateOf(0) }
    var isFirstTime by remember { mutableStateOf(true) }

    Column {
        Button(onClick = {
            Log.v("JordanRA","changing to: ${tabIndex + 1 < titles.size}")
            if (tabIndex + 1 < titles.size){
                tabIndex += 1
            }else{
                tabIndex = 0
            }
            Log.v("JordanRA","changing to: $tabIndex")
        }) {
            Text("change nex index")
        }
        TabRow(selectedTabIndex = tabIndex) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        Log.v("JordanRA","onClick: $index")
                              //tabIndex = index
                              },
                    onFocus = {
                        tabIndex = index
                        Log.v("JordanRA","onFocus: $index")
                    },
                    enabled = true,
                    modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp),
                    colors = TabDefaults.pillIndicatorTabColors(
                        selectedContentColor = Color.DarkGray.copy(alpha = 0.5f),
                    )
                ) {
                    Text(title, style = TextStyle.Default.copy(
                        fontSize = 30.sp
                    ))
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Selected page: ${titles[tabIndex]}", style = TextStyle.Default.copy(
                color = Color.White
            ))
        }
    }
}