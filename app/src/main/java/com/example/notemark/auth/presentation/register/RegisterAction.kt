package com.example.notemark.auth.presentation.register

sealed interface RegisterAction {

    data class UpdateOnScreenUserName(val userName: String): RegisterAction

    data class UpdateOnScreenEmail(val email: String): RegisterAction

    data class UpdateOnScreenPassword(val password: String): RegisterAction

    data class UpdateOnScreenRepeatPassword(val repeatPassword: String): RegisterAction

    data class UpdatePasswordVisibility(val isHidden: Boolean): RegisterAction

    data class UpdateRepeatPasswordVisibility(val isHidden: Boolean): RegisterAction

    data object GoToLogin: RegisterAction

    data object CreateAccount: RegisterAction

    data object ClearMessage: RegisterAction
}