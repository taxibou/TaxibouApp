plugins {
    id("com.taxibou.android.library")
    id("com.taxibou.compose")
}
android {
    namespace = "com.taxibou.app"
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation("androidx.core:core-splashscreen:1.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.11.0")
    implementation(project(":core:ui"))
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.11.0")
}

kotlin {
    this.compilerOptions {
        freeCompilerArgs.add("-Xexplicit-backing-fields")
    }
}