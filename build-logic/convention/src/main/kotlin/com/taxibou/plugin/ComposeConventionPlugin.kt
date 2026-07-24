package com.taxibou.plugin

import com.taxibou.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            dependencies {
                val composeBom = libs.findLib("androidx-compose-bom").get()
                implementation(platform(composeBom))
                implementation(libs.findBundle("compose-bundle").get())
            }
        }
    }
}