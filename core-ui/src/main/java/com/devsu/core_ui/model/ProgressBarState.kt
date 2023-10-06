package com.devsu.core_ui.model

sealed class ProgressBarState{
    object Loading: ProgressBarState()
    object Idle: ProgressBarState()
}