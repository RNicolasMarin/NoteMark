package com.example.notemark.presentation.ui.login

sealed interface LoginAction {

    data class UpdateOnScreenEmail(val email: String): LoginAction

    data class UpdateOnScreenPassword(val password: String): LoginAction

    data class UpdatePasswordVisibility(val isPasswordHidden: Boolean): LoginAction

    data object GoToRegister: LoginAction
}