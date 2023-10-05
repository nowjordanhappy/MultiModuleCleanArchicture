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
