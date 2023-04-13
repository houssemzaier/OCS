package extension

import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency


internal val VersionCatalog.javaxInject: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("javax.inject")

internal val VersionCatalog.kotlinJVMBundle: Provider<ExternalModuleDependencyBundle>
    get() = getBundle("kotlin_JVM_bundle")

internal val VersionCatalog.unitTestsBundle: Provider<ExternalModuleDependencyBundle>
    get() = getBundle("unitTestsBundle")

internal val VersionCatalog.composeBom: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("composeBom")

internal val VersionCatalog.composeActivity: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("composeActivity")
internal val VersionCatalog.composeLifecycleViewmodel: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("composeLifecycleViewmodel")
internal val VersionCatalog.composeNavigation: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("composeNavigation")
internal val VersionCatalog.composeMaterial3: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("composeMaterial3")
internal val VersionCatalog.composeToolingUiPreview: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("composeToolingUiPreview")
internal val VersionCatalog.composeDebugUiTooling: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("composeDebugUiTooling")

internal val VersionCatalog.hiltPlugin: Provider<PluginDependency>
    get() = getPlugin("hiltPlugin")


internal val VersionCatalog.logcat: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("logcat")

internal val VersionCatalog.ktlint: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("ktlint")

internal val VersionCatalog.playcore: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("androidx.playcore")


internal val VersionCatalog.kotlinxCollectionsImmutable: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("kotlinx.collections.immutable")

internal val VersionCatalog.composeRules: Provider<MinimalExternalModuleDependency>
    get() = getLibrary("composerules")

internal val VersionCatalog.composeBundle: Provider<ExternalModuleDependencyBundle>
    get() = getBundle("compose")


internal val VersionCatalog.composeTestBundle: Provider<ExternalModuleDependencyBundle>
    get() = getBundle("composetest")

private fun VersionCatalog.getLibrary(library: String) = findLibrary(library).get()

private fun VersionCatalog.getBundle(bundle: String) = findBundle(bundle).get()
private fun VersionCatalog.getPlugin(plugin: String) = findPlugin(plugin).get()
