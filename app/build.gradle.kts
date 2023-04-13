@file:Suppress("UnstableApiUsage")

import com.bravedroid.tools.CalculatorBuildType
import extension.SigningConfigHelper
import java.util.*

plugins {
    id("com.bravedroid.android.application")
    id("com.bravedroid.android.application.compose")
    id("com.bravedroid.android.application.flavors")
    id("com.bravedroid.android.application.jacoco")
    id("jacoco")
    id("com.bravedroid.android.hilt")
    id("com.bravedroid.android.application.firebase")
}

android {
    namespace = "com.orange.app"

    defaultConfig {
        applicationId = "com.bravedroid.calculator.android"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.orange.core.testing.OCSTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("signing-config") {
            val signing = SigningConfigHelper.getSigningConfigProperties(rootProject.file("/keys/keystore.properties"))
            keyAlias = signing.keyAlias
            keyPassword = signing.keyPassword
            storeFile = signing.storeFile
            storePassword = signing.storePassword
        }
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = CalculatorBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:design-system"))
    implementation(project(":core:cache"))
    implementation(project(":core:network"))
    implementation(project(":core:player"))

    implementation(project(":feature:home"))
    implementation(project(":feature:video"))

    androidTestImplementation(project(":core:testing"))
    testImplementation(project(":core:testing"))

    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.accompanist.testharness)
    androidTestImplementation(kotlin("test"))
    debugImplementation(libs.androidx.compose.ui.testManifest)

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.coil.kt)
    implementation(libs.androidx.constraintlayout)
}
