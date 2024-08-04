import com.devsu.buildsrc.Modules

apply {
    from("$rootDir/streaming-domain-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
}
