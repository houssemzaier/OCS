@file:Suppress("UnstableApiUsage")

include(":core:testing")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        includeBuild("build-logic")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "OCS"

val coreModules = arrayOf(":core:domain", ":core:design-system", ":core:testing", ":core:cache", ":core:network", ":core:player")
val featureModules = arrayOf(":feature:home", ":feature:video")
val modules = arrayOf(
    ":app",
    *coreModules,
    *featureModules,
)
include(*modules)
