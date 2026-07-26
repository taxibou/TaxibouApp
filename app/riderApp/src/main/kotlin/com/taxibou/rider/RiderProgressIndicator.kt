package com.taxibou.rider

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.taxibou.app.R
import kotlin.math.roundToInt


@Composable
fun CarProgressIndicator(
    modifier: Modifier = Modifier, animationDurationMillis: Int = 4000
) {
    // We use a single 0f -> 1f phase to represent the full round trip.
    // 0.0 to 0.5 = Moving Right
    // 0.5 to 1.0 = Moving Left
    val infiniteTransition = rememberInfiniteTransition(label = "CarTransition")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDurationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "CarPhase"
    )

    BoxWithConstraints(modifier = modifier.widthIn(min = 200.dp)) {
        // Convert the container's max width into pixels for translation math
        val maxContainerWidthPx = with(LocalDensity.current) { maxWidth.toPx() }

        Image(
            painter = painterResource(id = R.drawable.rider_ic_launcher_foreground), // Replace with your drawable
            contentDescription = "Loading indicator",
            modifier = Modifier
                .size(64.dp)
                .graphicsLayer {
                    // 1. Determine direction based on the current phase
                    val isMovingRight = phase < 0.5f

                    // 2. Calculate the fraction of the track (0.0f to 1.0f)
                    val trackFraction = if (isMovingRight) {
                        phase * 2f // Scales 0.0..0.5 up to 0.0..1.0
                    } else {
                        2f - (phase * 2f) // Scales 0.5..1.0 down to 1.0..0.0
                    }

                    // 3. Calculate exact X translation.
                    // 'size.width' here is the actual pixel width of the Image inside graphicsLayer.
                    val travelDistance = maxContainerWidthPx - size.width
                    translationX = travelDistance * trackFraction

                    // 4. Flip the car if moving right (since default image faces left)
                    scaleX = if (isMovingRight) -1f else 1f

                    transformOrigin = TransformOrigin.Center
                },
        )
    }
}