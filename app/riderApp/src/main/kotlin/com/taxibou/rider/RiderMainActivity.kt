package com.taxibou.rider

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.taxibou.app.MainActivity

class RiderMainActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
}

