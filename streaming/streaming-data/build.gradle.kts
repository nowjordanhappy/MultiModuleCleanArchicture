import com.devsu.buildsrc.Modules
import com.devsu.buildsrc.Retrofit

apply {
    from("$rootDir/streaming-data-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.streamingDomain))

    "implementation"(platform(Retrofit.okHttpBmo))
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.gsonConverter)
}