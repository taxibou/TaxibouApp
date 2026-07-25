plugins {
    id("com.taxibou.kotlin")
    id("com.taxibou.compose")
}

dependencies {
    api(libs.viewmodel.compose)
    api(project(":core:common"))
}
