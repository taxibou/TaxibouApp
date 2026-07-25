package com.taxibou.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(initialUiMode: UiMode, private val themeRepository: ThemeRepository) :
    ViewModel() {
    val uiMode = themeRepository.uiModeFlow.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), initialUiMode
    )

    fun updateUiMode(uiMode: UiMode) {
        viewModelScope.launch {
            themeRepository.updateUiMode(uiMode)
        }
    }
}
