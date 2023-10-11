import com.devsu.buildsrc.Modules
import com.devsu.buildsrc.Coil

apply {
    from("$rootDir/movie-ui-tv-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.movieDomain))

    "implementation"(Coil.coilKotlin)
}

/*android {
    namespace = "com.devsu.movie_ui_tv"
}*/
