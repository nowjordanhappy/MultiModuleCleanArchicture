import com.devsu.buildsrc.Modules
import com.devsu.buildsrc.Retrofit

apply {
    from("$rootDir/movie-data-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.movieDomain))

    "implementation"(platform(Retrofit.okHttpBmo))
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.gsonConverter)
}
