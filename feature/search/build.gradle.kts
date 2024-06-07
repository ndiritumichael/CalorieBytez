plugins {
    alias(libs.plugins.caloriebytez.android.feature)
    alias(libs.plugins.caloriebytez.library.compose)
}

android {
    namespace = "com.devmike.search"
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

    implementation(project(":core:data"))
    api(project(":core:common-ui"))
    implementation(project(":core:testing"))
}
