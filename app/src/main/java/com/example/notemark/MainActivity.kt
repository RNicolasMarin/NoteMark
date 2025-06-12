package com.example.notemark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.notemark.presentation.design_system.NoteMarkTheme

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

                    NavigationRoot(
                        navController = navController,
                        modifier = Modifier.padding(
                            start = innerPadding.calculateStartPadding(layoutDirection),
                            end = innerPadding.calculateEndPadding(layoutDirection),
                            bottom = innerPadding.calculateBottomPadding())
                    )
                }
            }
        }
    }
}