package com.devsu.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class NavigationManager {
    var commandSegments = MutableSharedFlow<NavigationCommandSegment>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun navigate(
        command: NavigationCommandSegment
    ) {
        println("NavigationManager: ${command.command.destination}")
        commandSegments.tryEmit(command)
    }

}