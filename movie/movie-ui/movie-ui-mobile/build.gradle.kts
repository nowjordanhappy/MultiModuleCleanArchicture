import com.devsu.buildsrc.Modules
import com.devsu.buildsrc.Coil
import com.devsu.buildsrc.DaggerHilt

apply {
    from("$rootDir/movie-ui-mobile-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.movieDomain))
    //"implementation"(project(Modules.movieData))

    "implementation"(Coil.coilCompose)
}
