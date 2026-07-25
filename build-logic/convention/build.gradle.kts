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
    implementation(libs.compose.compiler)
}
gradlePlugin {
    plugins {
        create("android-app-convention") {
            id = "com.taxibou.android.app"
            implementationClass = "com.taxibou.plugin.AndroidApplicationConventionPlugin"
        }
    }
    plugins {
        create("compose-convention") {
            id = "com.taxibou.compose"
            implementationClass = "com.taxibou.plugin.ComposeConventionPlugin"
        }
    }
    plugins {
        create("android-library-convention") {
            id = "com.taxibou.android.library"
            implementationClass = "com.taxibou.plugin.AndroidLibraryConventionPlugin"
        }
    }
    plugins {
        create("kotlin-library-convention") {
            id = "com.taxibou.kotlin"
            implementationClass = "com.taxibou.plugin.KotlinLibraryConventionPlugin"
        }
    }
}