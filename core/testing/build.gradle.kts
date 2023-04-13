plugins {
    id("com.bravedroid.android.library")
    id("com.bravedroid.android.library.compose")
    id("com.bravedroid.android.hilt")
}

android {
    namespace = "com.orange.core.testing"
}

dependencies {
    implementation(project(":core:domain"))

    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.junit4)
    api(libs.mockito.kotlin)
    api(libs.mockito.inline)
    api(libs.truth)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)

    debugApi(libs.androidx.compose.ui.testManifest)
}
