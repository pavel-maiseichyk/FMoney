plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version Deps.kotlinVersion
}

android {
    namespace = "com.pm.savings"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.pm.savings"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"//Deps.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
}

dependencies {
//    implementation(project(":shared"))
//    implementation(Deps.composeUi)
//    implementation(Deps.composeUiTooling)
//    implementation(Deps.composeUiToolingPreview)
//    implementation(Deps.composeFoundation)
//    implementation(Deps.composeMaterial)
//    implementation(Deps.activityCompose)
//    implementation(Deps.composeIconsExtended)
    implementation(Deps.composeNavigation)
    implementation(Deps.coilCompose)
    implementation(Deps.accompanistSystemUiController)
    val composeBom = platform("androidx.compose:compose-bom:2023.04.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("androidx.compose.material:material-icons-extended")

    implementation("androidx.core:core-splashscreen:1.1.0-alpha01")

//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

//    implementation("androidx.activity:activity-compose:1.6.1")


    implementation(Deps.dataStorePreferences)
    implementation(Deps.kotlinDateTime)

    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomCompiler)

    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltAndroidCompiler)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavigationCompose)

}