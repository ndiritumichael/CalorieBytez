plugins {
    alias(libs.plugins.caloriebytez.android.library)
    alias(libs.plugins.room.compiler)
    alias(libs.plugins.caloriebytez.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.devmike.database"
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

    room {

        schemaDirectory("$projectDir/schemas")
    }
}
dependencies {

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.testing)
    implementation(libs.androidx.test.ext)
    ksp(libs.room.compiler)

    testApi(project(":core:testing"))
}
