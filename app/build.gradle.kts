import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

val properties = Properties()
val localPropertiesFile = project.rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    FileInputStream(localPropertiesFile).use { inputStream ->
        properties.load(inputStream)
    }
}

android {
    namespace = "com.seven_sheesh.greventure"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.seven_sheesh.greventure"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders["googleMapsApiKey"] = properties.getProperty("MAPS_API_KEY")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packagingOptions {
        resources {
            excludes += "META-INF/AL2.0"
            excludes += "META-INF/LGPL2.1"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Material3 Design
    implementation(libs.material3)

    // Architectural Components
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.fragment)
    implementation(libs.androidx.hilt.navigation.compose)

    // Google Maps
    implementation(libs.maps.compose)
    implementation(libs.play.services.location)
    implementation(libs.maps.compose.v290)
    implementation(libs.play.services.maps)

    // Accompanist
    implementation(libs.accompanist.permissions)

    //Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coroutines Lifecycle Scopes
    implementation(libs.androidx.lifecycle.runtime.ktx.v283)
    implementation(libs.androidx.lifecycle.runtime.compose)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Navigation Component
    implementation(libs.androidx.navigation.compose)

    // Coil
    implementation(libs.coil.compose)

    // SplashScreen
    implementation(libs.androidx.core.splashscreen)

    // Retrofit
    implementation(libs.retrofit)

    // JSON Converter
    implementation(libs.converter.gson)

    // Icon Extended
    implementation(libs.androidx.material.icons.extended.android)
}

kapt {
    correctErrorTypes = true
}
