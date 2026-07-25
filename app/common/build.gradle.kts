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
    implementation(libs.splashscreen)
    api(project(":core:ui"))
}

kotlin {
    this.compilerOptions {
        freeCompilerArgs.add("-Xexplicit-backing-fields")
    }
}