import com.devsu.buildsrc.Modules
import com.devsu.buildsrc.Coil
import com.devsu.buildsrc.Compose

apply {
    from("$rootDir/streaming-ui-tv-module.gradle")
}

dependencies {
    "implementation"(Compose.tvComposeFoundation)
    "implementation"(Compose.tvComposeMaterial)

    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.streamingDomain))

    "implementation"(Coil.coilCompose)
}
