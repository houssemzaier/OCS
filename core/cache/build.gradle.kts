plugins {
    id("com.bravedroid.android.library")
    id("com.bravedroid.android.library.jacoco")
    id("com.bravedroid.android.hilt")
}

android {
    namespace = "com.orange.core.cache"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
    lint {
        checkDependencies = true
    }
}
dependencies {
    implementation(project(":core:domain"))

    implementation(libs.androidx.dataStore.core)
    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(project(":core:testing"))
}
