package com.devsu.buildsrc

object Compose {
    const val composeVersion = "1.3.2"
    const val materialComposeVersion = "1.1.1"
    const val uiComposeVersion = "1.1.1"
    const val runtimeComposeVersion = "1.1.1"
    //const val composeCompilerVersion = "1.3.2"
    const val composeCompilerVersion = "1.4.1"
    const val material = "androidx.compose.material:material:$materialComposeVersion"
    const val ui = "androidx.compose.ui:ui:$uiComposeVersion"
    const val uiTooling = "androidx.compose.ui:ui-tooling:$uiComposeVersion"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$uiComposeVersion"
    const val runtime = "androidx.compose.runtime:runtime:$runtimeComposeVersion"
    const val compiler = "androidx.compose.compiler:compiler:$composeCompilerVersion"

    const val platformComposeBmo = "androidx.compose:compose-bom:2023.03.00"
    const val composeUiBmo = "androidx.compose.ui:ui"
    const val composeUiGraphicsBmo = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreviewBmo = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3Bmo = "androidx.compose.material3:material3"

    //private const val navigationVersion = "2.4.0-beta02"
    private const val navigationVersion = "2.5.1"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0-beta01"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    private const val activityComposeVersion = "1.4.0"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    private const val lifecycleVersion = "2.4.0"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"

    private const val tvComposeVersion = "1.0.0-alpha07"
    const val tvComposeFoundation = "androidx.tv:tv-foundation:$tvComposeVersion"
    const val tvComposeMaterial = "androidx.tv:tv-material:$tvComposeVersion"
}