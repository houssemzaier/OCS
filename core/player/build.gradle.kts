@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")


plugins {
    id("com.bravedroid.android.library")
    id("com.bravedroid.android.library.compose")
    id("com.bravedroid.android.hilt")
}

android {
    namespace = "com.orange.core.player"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
}
dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:design-system"))

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout.compose)

    api(libs.androidx.media3.exoplayer)
    api(libs.androidx.media3.exoplayer.dash)
    api(libs.androidx.media3.ui)

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    api(libs.coil.kt.compose)

    androidTestImplementation(project(":core:testing"))
}
