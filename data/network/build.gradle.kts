plugins {
    alias(libs.plugins.caloriebytez.android.library)
    alias(libs.plugins.kotlinX.serialization)
}

android {
    namespace = "com.devmike.network"
    compileSdk = 34

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        buildFeatures {
            buildConfig = true
        }
    }
}

dependencies {
    implementation(libs.bundles.ktor)
}
