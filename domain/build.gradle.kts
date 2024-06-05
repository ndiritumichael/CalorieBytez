plugins {
    alias(libs.plugins.caloriebytez.android.library)
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
}
