import com.devsu.buildsrc.ProjectConfig
import com.devsu.buildsrc.AndroidX
import com.devsu.buildsrc.DaggerHilt
import com.devsu.buildsrc.Modules
import com.devsu.buildsrc.Retrofit
import com.devsu.buildsrc.Fragment
import com.devsu.buildsrc.Leanback
import com.devsu.buildsrc.Compose

plugins {
    id(com.devsu.buildsrc.Plugins.androidApplication)
    id(com.devsu.buildsrc.Plugins.kotlinAndroid)
    id(com.devsu.buildsrc.Plugins.kotlinKapt)
    id(com.devsu.buildsrc.Plugins.daggerHiltAndroid)
}

android {
    namespace = ProjectConfig.appId
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        //kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules-debug.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    flavorDimensions("platform")

    productFlavors {
        create("mobile") {
            dimension = "platform"
        }

        create("tv") {
            dimension = "platform"
        }
    }


    sourceSets {
        getByName("mobile") {
            manifest.srcFile("src/mobile/AndroidManifest.xml")
        }
        getByName("tv") {
            manifest.srcFile("src/tv/AndroidManifest.xml")
        }
    }
}

dependencies {
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.fragment)
    implementation(AndroidX.activity)
    implementation(AndroidX.constraintLayout)
    implementation(Leanback.leanback)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltAndroidCompiler)

    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.movieUiTv))
    implementation(project(Modules.movieUiMobile))
    implementation(project(Modules.movieDomain))
    implementation(project(Modules.movieData))

    implementation(Retrofit.okHttpBmo)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gsonConverter)

    implementation(Fragment.navigationFragment)
    implementation(Fragment.navigationUi)

    implementation(Compose.compiler)
    implementation(Compose.runtime)
    implementation(Compose.ui)
    //implementation(Compose.material)
    /*implementation("androidx.compose.material3:material3:1.0.0")
    implementation(Compose.navigation)
    implementation(Compose.activityCompose)
    implementation(Compose.uiToolingPreview)*/

    implementation(Compose.activityCompose)
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3")

    implementation(Leanback.leanback)
}