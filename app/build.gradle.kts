import com.devsu.buildsrc.ProjectConfig
import com.devsu.buildsrc.AndroidX
import com.devsu.buildsrc.DaggerHilt
import com.devsu.buildsrc.Modules
import com.devsu.buildsrc.Retrofit
import com.devsu.buildsrc.Fragment
import com.devsu.buildsrc.Leanback

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

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules-debug.pro"
            )
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

}
/*plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.devsu.multimodulecleanarchitecture"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.devsu.multimodulecleanarchitecture"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.devsu.buildsrc.AndroidX.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("com.devsu.buildsrc.AndroidX.core:core-ktx:1.9.0")
    implementation("com.devsu.buildsrc.AndroidX.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("com.devsu.buildsrc.AndroidX.activity:activity-compose:1.7.0")
    implementation(platform("com.devsu.buildsrc.AndroidX.compose:compose-bom:2023.03.00"))
    implementation("com.devsu.buildsrc.AndroidX.compose.ui:ui")
    implementation("com.devsu.buildsrc.AndroidX.compose.ui:ui-graphics")
    implementation("com.devsu.buildsrc.AndroidX.compose.ui:ui-tooling-preview")
    implementation("com.devsu.buildsrc.AndroidX.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("com.devsu.buildsrc.AndroidX.test.ext:junit:1.1.5")
    androidTestImplementation("com.devsu.buildsrc.AndroidX.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("com.devsu.buildsrc.AndroidX.compose:compose-bom:2023.03.00"))
    androidTestImplementation("com.devsu.buildsrc.AndroidX.compose.ui:ui-test-junit4")
    debugImplementation("com.devsu.buildsrc.AndroidX.compose.ui:ui-tooling")
    debugImplementation("com.devsu.buildsrc.AndroidX.compose.ui:ui-test-manifest")
}*/