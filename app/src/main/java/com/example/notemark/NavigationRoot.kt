package com.example.notemark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.notemark.presentation.ui.Screen.*
import com.example.notemark.presentation.ui.landing.LandingScreenRoot
import com.example.notemark.presentation.ui.login.LoginScreenRoot
import com.example.notemark.presentation.ui.registration.RegistrationScreenRoot

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
            val args = it.toRoute<Login>()
            LoginScreenRoot(
                modifier = modifier.fillMaxSize(),
                messageRes = args.messageRes,
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
            RegistrationScreenRoot(
                modifier = modifier.fillMaxSize(),
                goToLogin = { messageRes ->
                    navController.navigate(Login(messageRes = messageRes)) {
                        popUpTo(Registration) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}