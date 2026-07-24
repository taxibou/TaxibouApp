import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}


dependencies {
    implementation(libs.agp)
}
gradlePlugin {
    plugins {
        create("android-app-convention") {
            id = "com.taxibou.android.app"
            implementationClass = "com.taxibou.plugin.AndroidApplicationConventionPlugin"
        }
    }
}