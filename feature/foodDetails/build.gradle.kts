plugins {
    alias(libs.plugins.caloriebytez.android.feature)
    alias(libs.plugins.caloriebytez.library.compose)
}

android {
    namespace = "com.devmike.fooddetails"
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
}
dependencies {
    implementation(project(":core:data"))
    api(project(":core:common-ui"))
}
