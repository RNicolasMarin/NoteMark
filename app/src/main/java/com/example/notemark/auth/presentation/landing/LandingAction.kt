package com.example.notemark.auth.presentation.landing

sealed interface LandingAction {

    data object OnRegisterClick: LandingAction

    data object OnLoginClick: LandingAction
}