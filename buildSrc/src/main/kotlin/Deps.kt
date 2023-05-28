object Deps {

    // COMPOSE

//    val composeBom = platform("androidx.compose:compose-bom:2023.03.00")
//    implementation(composeBom)
//    androidTestImplementation(composeBom)
//
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//
//    implementation("androidx.compose.material:material-icons-extended")
//
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
//
//    implementation("androidx.activity:activity-compose:1.6.1")


//    private const val activityComposeVersion = "1.6.1"
//    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
//
//    const val composeVersion = "1.4.0-alpha02"
//    const val composeUi = "androidx.compose.ui:ui:$composeVersion"
//    const val composeUiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
//    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
//    const val composeFoundation = "androidx.compose.foundation:foundation:$composeVersion"
//    const val composeIconsExtended =
//        "androidx.compose.material:material-icons-extended:$composeVersion"
//
//    private const val composeMaterialVersion = "1.0.1"
//    const val composeMaterial = "androidx.compose.material3:material3:$composeMaterialVersion"
//

    private const val composeNavigationVersion = "2.5.3"
    const val composeNavigation = "androidx.navigation:navigation-compose:$composeNavigationVersion"

    private const val coilComposeVersion = "2.1.0"
    const val coilCompose = "io.coil-kt:coil-compose:$coilComposeVersion"

    private const val accompanistVersion = "0.28.0"
    const val accompanistSystemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"

    // ROOM
    private const val room_version = "2.5.1"
    const val roomRuntime = "androidx.room:room-runtime:$room_version"
    const val roomCompiler = "androidx.room:room-compiler:$room_version"
    const val roomKtx = "androidx.room:room-ktx:$room_version"

    // KOTLIN DATE TIME
    private const val dateTimeVersion = "0.4.0"
    const val kotlinDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion"

    // DATASTORE
    private const val dataStoreVersion = "1.0.0"
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:$dataStoreVersion"

    // HILT
    private const val hiltVersion = "2.44"
    private const val hiltCompilerVersion = "1.0.0"
    const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:$hiltCompilerVersion"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltCompilerVersion"

    // KTOR
    private const val ktorVersion = "2.1.3"
    const val ktorCore = "io.ktor:ktor-client-core:$ktorVersion"
    const val ktorSerialization = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
    const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"

    // GRADLE PLUGINS
    const val kotlinVersion = "1.8.20"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

    private const val gradleVersion = "7.2.2"
    const val androidBuildTools = "com.android.tools.build:gradle:$gradleVersion"

    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
}