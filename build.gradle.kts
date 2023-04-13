@file:Suppress("DSL_SCOPE_VIOLATION")

import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.*

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.gms) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ktlint) apply true
}
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    repositories {
        mavenCentral()
    }
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        android.set(true)
        outputColorName.set("RED")

        reporters {
            reporter(HTML)
            reporter(CHECKSTYLE)
            reporter(SARIF)
            reporter(PLAIN)
            reporter(JSON)
        }
    }
}
// tasks.getByPath(":app:assembleDemoDebug").dependsOn("ktlintCheck")
