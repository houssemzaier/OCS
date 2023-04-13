plugins {
    id("com.bravedroid.android.feature")
    id("com.bravedroid.android.library.compose")
    id("com.bravedroid.android.library.jacoco")
}
android {
    namespace = "com.orange.feature.home"
}

dependencies {

    implementation(project(":core:player"))

    implementation(libs.androidx.fragment)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.compose.material3.windowSizeClass)

    implementation(libs.androidx.window.manager)
    implementation(libs.accompanist.adaptive)
    implementation(libs.androidx.navigation.compose)

    testImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:testing"))
}
