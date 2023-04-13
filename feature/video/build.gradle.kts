plugins {
    id("com.bravedroid.android.feature")
    id("com.bravedroid.android.library.compose")
    id("com.bravedroid.android.library.jacoco")
}
android {
    namespace = "com.orange.feature.video"
}

dependencies {

    implementation(project(":core:player"))

    implementation(libs.androidx.fragment)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)

    testImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:testing"))
}
