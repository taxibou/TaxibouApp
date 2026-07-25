plugins {
    id("com.taxibou.android.library")
}
android {
    namespace = "com.taxibou.location"
}

dependencies {
    implementation(libs.coroutines.core)
    api("com.google.android.gms:play-services-location:21.4.0")
}