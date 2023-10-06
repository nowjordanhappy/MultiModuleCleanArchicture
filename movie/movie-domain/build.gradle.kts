import com.devsu.buildsrc.Modules

apply {
    from("$rootDir/movie-domain-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
}
