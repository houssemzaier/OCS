@file:Suppress("UnstableApiUsage")

package com.bravedroid.tools

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project
import java.util.*

@Suppress("EnumEntryName")
enum class FlavorDimension {
    contentType
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
//@Suppress("EnumEntryName")
enum class CalculatorFlavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
) {
    DEMO(FlavorDimension.contentType),
    PROD(FlavorDimension.contentType),
    ;

    val lowerName = name.lowercase()
}

fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: CalculatorFlavor) -> Unit = {},
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.contentType.name
        productFlavors {
            CalculatorFlavor.values().forEach { flavor: CalculatorFlavor ->
                create(flavor.lowerName) {
                    dimension = flavor.dimension.name
                    flavorConfigurationBlock(this, flavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (flavor.applicationIdSuffix != null) {
                            this.applicationIdSuffix = flavor.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
