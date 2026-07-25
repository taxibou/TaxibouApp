package com.taxibou.rider

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.taxibou.UiMode
import com.taxibou.app.MainActivity
import com.taxibou.ui.TaxibouTheme

class RiderMainActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppComposable {
            RiderApp(it)
        }
    }
}

@Composable
fun RiderApp(uiMode: UiMode) {
    TaxibouTheme(uiMode, lightColorScheme(), darkColorScheme()) {
        Surface(Modifier.fillMaxSize()) { }
    }

}