package pe.com.master.machines.splash.screen

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import pe.com.master.machines.design.ui.components.custom.CircleAnimation
import pe.com.master.machines.design.ui.components.image.ImageLoading
import pe.com.master.machines.design.ui.theme.ColorWhite

@Composable
fun SplashScreen(navigateToHome: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorWhite),
        contentAlignment = Alignment.Center
    ) {
        CircleAnimation(colorCircle = MaterialTheme.colorScheme.primary)
        ImageLoading(
            primaryColor = MaterialTheme.colorScheme.secondary,
            contentScale = ContentScale.Fit,
        )
    }

    Handler(Looper.getMainLooper()).postDelayed({
        navigateToHome()
    }, 3000)

}