package com.taxibou.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taxibou.UiMode
import com.taxibou.ui.ThemeViewModel

abstract class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel> { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.uiState.value.isSplashScreen
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    fun setAppComposable(content: @Composable StartScope.() -> Unit) {
        setContent {
            val mainUiState by mainViewModel.uiState.collectAsState()
            when (val state = mainUiState) {
                MainUiState.SplashScreen -> {}
                is MainUiState.Success -> {
                    val themeViewModel = viewModel<ThemeViewModel> {
                        ThemeViewModel(state.uiMode, InMemoryThemeRepository())
                    }
                    val uiMode by themeViewModel.uiMode.collectAsState()
                    content(object : StartScope {
                        override val uiMode: UiMode = uiMode
                    })
                }
            }
        }
    }
}