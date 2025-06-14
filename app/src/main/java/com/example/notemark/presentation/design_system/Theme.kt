package com.example.notemark.presentation.design_system

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.notemark.presentation.design_system.ScreenConfiguration.LANDSCAPE
import com.example.notemark.presentation.design_system.ScreenConfiguration.PORTRAIT
import com.example.notemark.presentation.design_system.ScreenConfiguration.TABLET

private val ColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = White,
    onSurface = Black1,
    onSurfaceVariant = Black2,
    surface = Grey2,
    error = Red,
    surfaceContainerLowest = White
)

@Composable
fun NoteMarkTheme(
    content: @Composable () -> Unit
) {

    val configuration = LocalConfiguration.current
    val heightDp = configuration.screenHeightDp
    val widthDp = configuration.screenWidthDp

    Log.d("ScreenSize", "widthDp = $widthDp, heightDp = $heightDp")

    val screenConfiguration = when {
        widthDp > heightDp -> LANDSCAPE
        widthDp < 840 && heightDp < 900 -> PORTRAIT
        else -> TABLET
    }

    val dimens = when (screenConfiguration) {
        PORTRAIT -> dimensPortrait
        LANDSCAPE -> dimensLandscape
        TABLET -> dimensPortrait
    }

    ProvideDimens(dimens) {
        ProvideScreenConfiguration(screenConfiguration) {
            MaterialTheme(
                colorScheme = ColorScheme,
                typography = Typography,
                content = content
            )
        }
    }

}