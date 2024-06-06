plugins {
    alias(libs.plugins.caloriebytez.android.library)
    alias(libs.plugins.kotlinX.serialization)
}

android {
    namespace = "com.devmike.domain"

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
    implementation(libs.kotlinx.serialization.json)
}
