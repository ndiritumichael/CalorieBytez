import java.util.Properties

plugins {
    alias(libs.plugins.caloriebytez.android.library)
    alias(libs.plugins.kotlinX.serialization)
    alias(libs.plugins.caloriebytez.android.hilt)
}

android {
    namespace = "com.devmike.network"
    compileSdk = 34
    defaultConfig {

        val keystoreFile = project.rootProject.file("local.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        // return empty key in case something goes wrong
        val apiKey = properties.getProperty("API_KEY") ?: ""

        buildConfigField("String", "API_KEY", " \"$apiKey\"")
        buildConfigField("String", "HOST_URL", "\"api.calorieninjas.com/v1\"")
    }

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

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.kotlinx.serialization.json)
    api(project(":domain"))
    testApi(project(":core:testing"))
}
