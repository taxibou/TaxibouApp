package com.taxibou.common

import com.taxibou.implementation
import com.taxibou.plugin.findLib
import com.taxibou.plugin.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun Project.addCoreAndroidLibraries(dependencyHandlerScope: DependencyHandlerScope) {
    with(dependencyHandlerScope) {
        implementation(libs.findLib("androidx-core"))
        implementation(libs.findLib("androidx-activity-compose"))
    }
}