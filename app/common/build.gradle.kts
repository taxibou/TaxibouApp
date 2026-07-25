plugins {
    id("com.taxibou.android.library")
}
android {
    namespace = "com.taxibou.app"
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
}