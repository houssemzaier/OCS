@file:Suppress("UnstableApiUsage")

package com.bravedroid.tools

@Suppress("unused")
enum class CalculatorBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
