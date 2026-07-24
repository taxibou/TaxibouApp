package com.taxibou.plugin

import com.android.build.api.dsl.LibraryExtension
import com.taxibou.common.configureAndroidCommon
import com.taxibou.common.configureKotlinAndroidCommon
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("Unused")
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            extensions.configure<LibraryExtension> {
                configureAndroidCommon()
            }
            configureKotlinAndroidCommon()
        }
    }
}
