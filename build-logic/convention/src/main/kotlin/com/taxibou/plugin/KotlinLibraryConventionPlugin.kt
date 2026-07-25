package com.taxibou.plugin

import com.taxibou.Constants
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

@Suppress("Unused")
class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.jvm")
            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = Constants.javaVersion
                targetCompatibility = Constants.javaVersion
            }
            extensions.configure<KotlinJvmProjectExtension> {
                jvmToolchain(Constants.JVM_TOOLCHAIN)
                compilerOptions {
                    jvmTarget.set(Constants.jvmTarget)
                }
            }
        }
    }
}