package com.example.notemark.presentation.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notemark.domain.core.EmailValidator
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.*
import com.example.notemark.presentation.design_system.NoteMarkButtonState
import com.example.notemark.presentation.ui.login.LoginAction.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailValidator: EmailValidator
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onAction(action: LoginAction) {
        when (action) {
            is UpdateOnScreenEmail -> {
                state = state.copy(
                    email = state.email.copy(
                        text = action.email
                    ),
                    buttonState = getButtonState(
                        email = action.email
                    )
                )
            }
            is UpdateOnScreenPassword -> {
                state = state.copy(
                    password = state.password.copy(
                        text = action.password
                    ),
                    buttonState = getButtonState(
                        password = action.password
                    )
                )
            }

            is UpdatePasswordVisibility -> {
                state = state.copy(
                    password = state.password.copy(
                        visibility = if (action.isHidden) HIDDEN else SHOWN
                    )
                )
            }
            GoToRegister -> Unit
        }
    }

    private fun getButtonState(
        email: String = state.email.text,
        password: String = state.password.text,
    ): NoteMarkButtonState {
        if (email.isBlank() || password.isBlank()) return NoteMarkButtonState.DISABLE

        val isValidEmail = emailValidator.isValidEmail(email)
        return if (isValidEmail) {
            NoteMarkButtonState.ENABLE
        } else {
            NoteMarkButtonState.DISABLE
        }
    }
}