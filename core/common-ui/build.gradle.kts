plugins {
    alias(libs.plugins.caloriebytez.android.feature)
    alias(libs.plugins.caloriebytez.library.compose)
}

android {
    namespace = "com.devmike.commonui"
    compileSdk = 34

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    api(project(":domain"))
}
