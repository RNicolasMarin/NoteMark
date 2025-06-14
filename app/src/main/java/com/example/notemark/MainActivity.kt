package com.example.notemark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.notemark.presentation.design_system.NoteMarkTheme
import com.example.notemark.presentation.design_system.ScreenConfiguration
import com.example.notemark.presentation.design_system.screenConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            NoteMarkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()
                    val layoutDirection = LocalLayoutDirection.current

                    val start: Dp
                    val end: Dp
                    val bottom: Dp

                    if (MaterialTheme.screenConfiguration == ScreenConfiguration.PHONE_LANDSCAPE) {
                        start = innerPadding.calculateStartPadding(layoutDirection)
                        end = innerPadding.calculateEndPadding(layoutDirection)
                        bottom = 0.dp
                    } else {
                        start = innerPadding.calculateStartPadding(layoutDirection)
                        end = innerPadding.calculateEndPadding(layoutDirection)
                        bottom = innerPadding.calculateBottomPadding()
                    }

                    NavigationRoot(
                        navController = navController,
                        modifier = Modifier.padding(
                            start = start,
                            end = end,
                            bottom = bottom
                        )
                    )
                }
            }
        }
    }
}