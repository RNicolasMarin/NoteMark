package com.example.notemark.presentation.ui.landing

sealed interface LandingAction {

    data object GoToRegister: LandingAction

    data object GoToLogin: LandingAction
}