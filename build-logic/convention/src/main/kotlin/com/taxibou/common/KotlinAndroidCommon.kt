package com.taxibou.common

import com.taxibou.Constants
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidExtension

fun Project.configureKotlinAndroidCommon() = extensions.configure<KotlinAndroidExtension> {
    jvmToolchain(Constants.JVM_TOOLCHAIN)
    compilerOptions {
        jvmTarget.set(Constants.jvmTarget)
    }
}