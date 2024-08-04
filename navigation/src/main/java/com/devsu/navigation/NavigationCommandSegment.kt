package com.devsu.navigation

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator

sealed class NavigationCommandSegment {
    abstract val command: NavigationCommand

    data class DefaultNavigation(
        override val command: NavigationCommand,
        val navOptions: NavOptions? = null,
        val navigatorExtras: Navigator.Extras? = null
    ) : NavigationCommandSegment()

    data class BuilderNavigation(
        override val command: NavigationCommand,
        val builder: NavOptionsBuilder.() -> Unit
    ) : NavigationCommandSegment()
}