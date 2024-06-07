plugins {
    alias(libs.plugins.caloriebytez.android.feature)
    alias(libs.plugins.caloriebytez.library.compose)
}

android {
    namespace = "com.devmike.testing"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    api(libs.ktor.mock)
    api(libs.androidx.arch.core.testing)
    api(libs.kotlinx.coroutines.test)
    api(libs.core.ktx)
    api(libs.truth)
    api(libs.robolectric)
    api(libs.mockk)
    implementation(project(":domain"))
}
