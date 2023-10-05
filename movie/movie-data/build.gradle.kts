import com.devsu.buildsrc.Modules
import org.gradle.api.Project

//project.properties["myNamespace"] = "com.devsu.movie_data"
apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
}

/*android {
    namespace = 'com.devsu.movie_data'
}*/
