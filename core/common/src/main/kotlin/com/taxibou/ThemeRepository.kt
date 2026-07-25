package com.taxibou

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val uiModeFlow: Flow<UiMode>

    suspend fun updateUiMode(mode: UiMode)
}