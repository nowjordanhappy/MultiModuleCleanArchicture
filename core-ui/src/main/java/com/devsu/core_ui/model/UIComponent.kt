package com.devsu.core_ui.model

sealed class UIComponent{
    //For logging or sending analytics
    data class None(
        val message: String
    ): UIComponent()
}