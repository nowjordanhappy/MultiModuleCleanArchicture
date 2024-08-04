pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri ("https://www.jitpack.io") }
    }
}

rootProject.name = "MultiModuleCleanArchicture"
include(":app")
include(":movie")
include(":movie:movie-data")
include(":movie:movie-domain")
include(":movie:movie-ui")
include(":movie:movie-ui:movie-ui-tv")
include(":movie:movie-ui:movie-ui-mobile")
include(":core")
include(":core-ui")
include(":streaming")
include(":streaming:streaming-data")
include(":streaming:streaming-domain")
include(":streaming:streaming-ui")
include(":streaming:streaming-ui:streaming-ui-tv")
include(":navigation")
include(":preferences")
