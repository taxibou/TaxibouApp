package com.taxibou.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

abstract class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel> { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.uiState.value.isSplashScreen
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }
}