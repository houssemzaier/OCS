@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")


plugins {
    id("com.bravedroid.android.library")
    id("com.bravedroid.android.hilt")
    id("com.bravedroid.android.library.jacoco")
    id("kotlinx-serialization")
}

android {
    namespace = "com.orange.core.network"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
}
dependencies {
    implementation(project(":core:domain"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.facebook.stetho)
    implementation(libs.facebook.stetho.okhttp)

    testImplementation(project(":core:testing"))
}
