package com.taxibou

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Constants {

    const val COMPILE_SDK = 37
    const val MIN_SDK = 24

    const val JVM_TOOLCHAIN = 21
    val jvmTarget = JvmTarget.JVM_21
}