plugins {
    alias(libs.plugins.caloriebytez.android.library)
    alias(libs.plugins.kotlinX.serialization)
    alias(libs.plugins.caloriebytez.android.hilt)
}

android {
    namespace = "com.devmike.data"
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
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    testImplementation(project(":core:testing"))
}
