plugins {
    id("kotlin")
}

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.javax.inject)
    testImplementation(libs.bundles.unitTests)
    testImplementation(libs.kotlinx.coroutines.test)
}
