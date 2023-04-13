package extension

import java.io.File
import java.io.FileInputStream
import java.util.*

object SigningConfigHelper {
    fun getSigningConfigProperties(propertiesFile: File): Signing {
        return if (propertiesFile.exists()) {
            val properties = Properties().apply {
                load(FileInputStream(propertiesFile))
            }
            Signing.LOCAL(properties)
        } else {
            Signing.CI
        }
    }
}

sealed class Signing(
    val keyAlias: String,
    val keyPassword: String,
    val storeFile: File,
    val storePassword: String,
) {
    object CI : Signing(
        keyAlias = System.getenv("BITRISEIO_ANDROID_KEYSTORE_ALIAS"),
        keyPassword = System.getenv("BITRISEIO_ANDROID_KEYSTORE_PRIVATE_KEY_PASSWORD"),
        storeFile = File(System.getenv("HOME") + "/keystores/my_keystore.jks"),
        storePassword = System.getenv("BITRISEIO_ANDROID_KEYSTORE_PASSWORD"),
    )

    class LOCAL(properties: Properties) : Signing(
        keyAlias = properties.getProperty("keyAlias"),
        keyPassword = properties.getProperty("keyPassword"),
        storeFile = File(properties.getProperty("storeFile")),
        storePassword = properties.getProperty("storePassword"),
    )
}
