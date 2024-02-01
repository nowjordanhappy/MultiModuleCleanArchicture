import com.devsu.buildsrc.AndroidX

apply{
    from("$rootDir/preferences-module.gradle")
}

dependencies {
    "implementation"(AndroidX.datastore)
}
