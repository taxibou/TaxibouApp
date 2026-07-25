package com.taxibou.driver


import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Modifier
import com.taxibou.app.MainActivity
import com.taxibou.ui.TaxibouTheme

class DriverMainActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppComposable {
            TaxibouTheme(it, lightColorScheme(), darkColorScheme()) {
                Surface(Modifier.fillMaxSize()) { }
            }
        }
    }
}
