package com.example.notemark.auth.presentation.login

sealed interface LoginAction {

    data class UpdateOnScreenEmail(val email: String): LoginAction

    data class UpdateOnScreenPassword(val password: String): LoginAction

    data class UpdatePasswordVisibility(val isHidden: Boolean): LoginAction

    data object OnRegisterClick: LoginAction

    data object ClearMessage: LoginAction

    data object LoginAccount: LoginAction
}