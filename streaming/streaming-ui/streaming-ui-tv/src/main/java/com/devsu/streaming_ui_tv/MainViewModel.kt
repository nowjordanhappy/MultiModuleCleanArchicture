package com.devsu.streaming_ui_tv

import androidx.lifecycle.ViewModel
import com.devsu.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigationManager: NavigationManager
): ViewModel() {
    val navigationCommandSegments = navigationManager.commandSegments
}