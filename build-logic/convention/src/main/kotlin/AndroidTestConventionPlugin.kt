import com.android.build.gradle.TestExtension
import com.bravedroid.tools.CalculatorVersions
import com.bravedroid.tools.configureGradleManagedDevices
import com.bravedroid.tools.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = CalculatorVersions.targetSdk
                configureGradleManagedDevices(this)
            }
        }
    }

}
