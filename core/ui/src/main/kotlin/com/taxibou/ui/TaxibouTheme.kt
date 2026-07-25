package com.taxibou.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.taxibou.UiMode

@Composable
fun TaxibouTheme(
    uiMode: UiMode,
    lightColorScheme: ColorScheme,
    darkColorScheme: ColorScheme,
    content: @Composable () -> Unit
) {
    val colorScheme = when (uiMode) {
        UiMode.System -> if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme
        UiMode.Light -> lightColorScheme
        UiMode.Dark -> darkColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme, content = content
    )
}
