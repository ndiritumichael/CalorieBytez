plugins {

    alias(libs.plugins.caloriebytez.application.compose)
    alias(libs.plugins.caloriebytez.android.hilt)
}

android {
    namespace = "com.devmike.caloriebytez"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.devmike.caloriebytez"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
