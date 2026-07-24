package com.taxibou.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.taxibou.Constants
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidExtension

@Suppress("Unused")
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = Constants.COMPILE_SDK
                defaultConfig {
                    minSdk = Constants.MIN_SDK
                    targetSdk = Constants.COMPILE_SDK
                }
                buildFeatures {
                    compose = true
                }
                compileOptions {
                    sourceCompatibility = Constants.javaVersion
                    targetCompatibility = Constants.javaVersion
                }
            }
            extensions.configure<KotlinAndroidExtension> {
                jvmToolchain(Constants.JVM_TOOLCHAIN)
                compilerOptions {
                    jvmTarget.set(Constants.jvmTarget)
                }
            }
        }
    }
}