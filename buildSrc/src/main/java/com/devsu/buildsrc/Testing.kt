package com.devsu.buildsrc

object Testing {
    private const val junitVersion = "4.13.2"
    const val junit4 = "junit:junit:$junitVersion"

    private const val junitAndroidExtVersion = "1.1.3"
    const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"

    private const val coroutinesTestVersion = "1.6.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"

    private const val truthVersion = "1.1.3"
    const val truth = "com.google.truth:truth:$truthVersion"

    //private const val mockkVersion = "1.9.1"
    private const val mockkVersion = "1.13.3"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"

    private const val mockWebServerVersion = "4.9.3"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockWebServerVersion"
    //private const val composeJUnit4Version = "1.1.1"

    private const val composeJUnit4Version = "1.1.0"
    //private const val composeJUnit4Version = "1.2.0-rc03"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${composeJUnit4Version}"

    private const val expressoVersion = "3.3.0"
    const val expresso = "androidx.test.espresso:espresso-core:${expressoVersion}"

    const val hiltTesting = "com.google.dagger:hilt-android-testing:${DaggerHilt.version}"

    private const val testRunnerVersion = "1.4.0"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"

    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Kotlin.version}"

    private const val coreTestingVersion = "2.1.0"
    const val coreTesting = "androidx.arch.core:core-testing:${coreTestingVersion}"
}