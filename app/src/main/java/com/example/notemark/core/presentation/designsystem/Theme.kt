package com.example.notemark.core.presentation.designsystem

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.notemark.core.presentation.designsystem.ScreenConfiguration.*

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
        widthDp > heightDp && heightDp > 600 -> TABLET_LANDSCAPE
        widthDp > heightDp -> PHONE_LANDSCAPE
        widthDp < 840 && heightDp < 900 -> PHONE_PORTRAIT
        else -> TABLET_PORTRAIT
    }

    val dimens = when (screenConfiguration) {
        PHONE_PORTRAIT -> dimensPortrait
        PHONE_LANDSCAPE -> dimensLandscape
        TABLET_PORTRAIT -> dimensTabletPortrait
        TABLET_LANDSCAPE -> dimensTabletLandscape
    }

    val typography = when {
        screenConfiguration == TABLET_LANDSCAPE -> TypographyTabletLandscape
        else -> Typography
    }

    ProvideDimens(dimens) {
        ProvideScreenConfiguration(screenConfiguration) {
            MaterialTheme(
                colorScheme = ColorScheme,
                typography = typography,
                content = content
            )
        }
    }

}