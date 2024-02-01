import com.devsu.buildsrc.Modules
import com.devsu.buildsrc.Coil
import com.devsu.buildsrc.Compose
import com.devsu.buildsrc.ExoPlayer

apply {
    from("$rootDir/streaming-ui-tv-module.gradle")
}

dependencies {
    "implementation"(Compose.tvComposeFoundation)
    "implementation"(Compose.tvComposeMaterial)

    "implementation"(Compose.materialComposeIconsExtended)

    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.streamingDomain))

    "implementation"(project(Modules.navigation))

    "implementation"(Coil.coilCompose)
    "implementation"("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28")

    "implementation"(ExoPlayer.exoPlayer)
    "implementation"(ExoPlayer.exoPlayerUi)
    "implementation"(ExoPlayer.exoplayerHls)
}
