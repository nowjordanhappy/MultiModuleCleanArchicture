buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (com.devsu.buildsrc.Build.androidBuildTools)
        classpath (com.devsu.buildsrc.Build.hiltAndroidGradlePlugin)
        classpath (com.devsu.buildsrc.Build.kotlinGradlePlugin)
        classpath (com.devsu.buildsrc.Build.navigationSafeArg)

    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("com.android.library") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}