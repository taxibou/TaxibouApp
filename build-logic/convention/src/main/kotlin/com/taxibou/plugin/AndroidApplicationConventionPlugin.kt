package com.taxibou.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.taxibou.Constants
import com.taxibou.api
import com.taxibou.common.addCoreAndroidLibraries
import com.taxibou.common.configureAndroidCommon
import com.taxibou.common.configureKotlinAndroidCommon
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("Unused")
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    targetSdk = Constants.COMPILE_SDK
                }
                buildFeatures {
                    compose = true
                }
                configureAndroidCommon()

            }
            configureKotlinAndroidCommon()
            dependencies {
                addCoreAndroidLibraries(this)
                api(project(":app:common"))
            }
        }
    }
}