package com.taxibou.rider

import android.os.Bundle
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
        Box(Modifier.fillMaxSize()) {
            var offset by remember { mutableFloatStateOf(0f) }
            Canvas(Modifier.fillMaxSize()) {
                val spawnPoint = Offset(size.width / 2, 0f + offset)
                drawCircle(Color.Black, radius = 100f, center = spawnPoint)
            }
            Button({

                offset += 10f
            }, Modifier.align(Alignment.Center)) {
                Text("Click me")
            }
        }
    }

}