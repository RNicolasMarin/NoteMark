package com.example.notemark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notemark.presentation.ui.Screen.*
import com.example.notemark.presentation.ui.landing.LandingScreenRoot
import com.example.notemark.presentation.ui.login.LoginScreenRoot
import com.example.notemark.presentation.ui.registration.RegistrationScreen

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
            LandingScreenRoot(
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
            LoginScreenRoot(
                modifier = modifier.fillMaxSize(),
                goToRegistration = {
                    navController.navigate(Registration) {
                        popUpTo(Login) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Registration> {
            RegistrationScreen(
                modifier = modifier.fillMaxSize(),
                goToLogin = {
                    navController.navigate(Login) {
                        popUpTo(Registration) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}