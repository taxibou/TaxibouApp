package com.taxibou.common

import com.android.build.api.dsl.CommonExtension
import com.taxibou.Constants

fun CommonExtension.configureAndroidCommon() {
    compileSdk = Constants.COMPILE_SDK
    with(defaultConfig) {
        minSdk = Constants.MIN_SDK
    }
    with(compileOptions) {
        sourceCompatibility = Constants.javaVersion
        targetCompatibility = Constants.javaVersion
    }
}