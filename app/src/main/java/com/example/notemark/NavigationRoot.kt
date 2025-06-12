package com.example.notemark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notemark.presentation.ui.Screen.Landing
import com.example.notemark.presentation.ui.landing.LandingScreen

@Composable
fun NavigationRoot(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Landing
    ) {
        composable<Landing> {
            LandingScreen(modifier = modifier.fillMaxSize())
        }
    }
}