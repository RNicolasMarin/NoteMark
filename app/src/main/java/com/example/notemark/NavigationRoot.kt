package com.example.notemark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notemark.presentation.design_system.screenConfiguration
import com.example.notemark.presentation.ui.Screen.Landing

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
            Column(modifier = modifier.fillMaxSize()) {
                Text(text = MaterialTheme.screenConfiguration.name)
            }
        }
    }
}