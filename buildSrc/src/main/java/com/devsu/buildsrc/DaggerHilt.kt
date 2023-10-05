package com.devsu.buildsrc

object DaggerHilt {
    private const val version = "2.44"
    const val hiltCompilerVersion = "1.0.0"
    const val hiltCompiler= "androidx.hilt:hilt-compiler:$hiltCompilerVersion"
    const val hiltAndroid = "com.google.dagger:hilt-android:$version"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
    private const val hiltNavFragmentVersion = "1.0.0"
    const val hiltNavFragment = "androidx.hilt:hilt-navigation-fragment:$hiltNavFragmentVersion"

    private const val hiltLifecycleVMVersion = "1.0.0-alpha034"
    const val hiltLifecycleVM = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltLifecycleVMVersion"
}