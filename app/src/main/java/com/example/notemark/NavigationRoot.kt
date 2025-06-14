package com.example.notemark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notemark.presentation.ui.Screen.*
import com.example.notemark.presentation.ui.landing.LandingScreen
import com.example.notemark.presentation.ui.login.LoginScreen
import com.example.notemark.presentation.ui.register.RegistrationScreen

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
            LandingScreen(
                modifier = modifier.fillMaxSize(),
                goToLogin = {
                    navController.navigate(Login) {
                        popUpTo(Landing) {
                            inclusive = true
                        }
                    }
                },
                goToRegister = {
                    navController.navigate(Registration) {
                        popUpTo(Landing) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Login> {
            LoginScreen(
                modifier = modifier.fillMaxSize(),
            )
        }
        composable<Registration> {
            RegistrationScreen(
                modifier = modifier.fillMaxSize(),
            )
        }
    }
}