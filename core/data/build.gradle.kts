plugins {
    id("com.taxibou.android.library")
}

android {
    namespace = "com.taxibou.data"
}

dependencies {
    implementation("androidx.datastore:datastore-preferences:1.2.1")
    implementation(project(":core:common"))
}