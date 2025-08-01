package com.example.notemark.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.notemark.app.Screen.*
import com.example.notemark.auth.presentation.landing.LandingScreenRoot
import com.example.notemark.auth.presentation.login.LoginScreenRoot
import com.example.notemark.notes.presentation.notes.NotesScreen
import com.example.notemark.auth.presentation.register.RegistrationScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Landing
    ) {
        composable<Landing> {
            LandingScreenRoot(
                onLoginClick = {
                    navController.navigate(Login(messageRes = -1)) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Registration) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable<Login> {
            val args = it.toRoute<Login>()
            LoginScreenRoot(
                messageRes = args.messageRes,
                onRegisterClick = {
                    navController.navigate(Registration) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onLoginSuccess = {
                    navController.navigate(Notes) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable<Registration> {
            RegistrationScreenRoot(
                onLoginClick = { messageRes ->
                    navController.navigate(Login(messageRes = messageRes)) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable<Notes> {
            NotesScreen()
        }
    }
}