package com.devsu.buildsrc

object Build {
    //private const val androidBuildToolsVersion = "7.1.3"
    private const val androidBuildToolsVersion = "8.0.0"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    private const val hiltAndroidGradlePluginVersion = "2.38.1"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"

    const val navigationSafeArg = "androidx.navigation:navigation-safe-args-gradle-plugin:${Fragment.navVersion}"
}