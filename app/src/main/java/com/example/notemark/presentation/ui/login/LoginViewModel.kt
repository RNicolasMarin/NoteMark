package com.example.notemark.presentation.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notemark.presentation.design_system.NoteMarkButtonState
import com.example.notemark.presentation.ui.login.LoginAction.*

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onAction(action: LoginAction) {
        when (action) {
            is UpdateOnScreenEmail -> {
                state = state.copy(
                    email = action.email,
                    buttonState = getButtonState(
                        email = action.email,
                        password = state.password
                    )
                )
            }
            is UpdateOnScreenPassword -> {
                state = state.copy(
                    password = action.password,
                    buttonState = getButtonState(
                        email = state.email,
                        password = action.password
                    )
                )
            }

            is UpdatePasswordVisibility -> {
                state = state.copy(
                    isPasswordHidden = action.isPasswordHidden
                )
            }
        }
    }

    private fun getButtonState(email: String, password: String): NoteMarkButtonState {
        if (email.isBlank() || password.isBlank()) return NoteMarkButtonState.DISABLE

        val isValidEmail = email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"))
        return if (isValidEmail) {
            NoteMarkButtonState.ENABLE
        } else {
            NoteMarkButtonState.DISABLE
        }
    }
}