import com.devsu.buildsrc.Modules
import com.devsu.buildsrc.Coil

apply {
    from("$rootDir/movie-ui-mobile-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.movieDomain))
    //"implementation"(project(Modules.movieData))

    "implementation"(Coil.coilKotlin)

}
/*
android {
    namespace = "com.devsu.movie_ui_mobile"
}
*/
