package com.example.notemark.presentation.ui.registration

sealed interface RegistrationAction {

    data class UpdateOnScreenUserName(val userName: String): RegistrationAction

    data class UpdateOnScreenEmail(val email: String): RegistrationAction

    data class UpdateOnScreenPassword(val password: String): RegistrationAction

    data class UpdateOnScreenRepeatPassword(val repeatPassword: String): RegistrationAction

    data class UpdatePasswordVisibility(val isHidden: Boolean): RegistrationAction

    data class UpdateRepeatPasswordVisibility(val isHidden: Boolean): RegistrationAction

    data object GoToLogin: RegistrationAction

    data object CreateAccount: RegistrationAction

    data object ClearMessage: RegistrationAction
}