plugins {
    id("com.taxibou.android.app")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.taxibou.rider"
    defaultConfig {
        applicationId = "com.taxibou.rider"
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.bundle)
}