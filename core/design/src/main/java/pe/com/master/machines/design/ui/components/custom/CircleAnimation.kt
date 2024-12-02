package pe.com.master.machines.design.ui.components.custom

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.max

@Composable
fun CircleAnimation(colorCircle: Color) {

    val transitionState = remember { MutableTransitionState(false) }
    transitionState.targetState = true

    val transition = rememberTransition(transitionState, label = "Circle Animation")

    val radius by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 15000,
                easing = LinearOutSlowInEasing
            )
        }, label = "Circle Radius"
    ) { state ->
        if (state) 3f else 0f
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val maxRadius = max(maxWidth.value, maxHeight.value)
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = colorCircle, radius = radius * maxRadius
            )
        }
    }
}

@Preview
@Composable
fun getPreview() {
    CircleAnimation(colorCircle = MaterialTheme.colorScheme.primary)
}