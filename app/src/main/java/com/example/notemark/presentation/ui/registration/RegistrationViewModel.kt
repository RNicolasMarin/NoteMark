package com.example.notemark.presentation.ui.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notemark.domain.core.EmailValidator
import com.example.notemark.domain.core.PasswordValidator
import com.example.notemark.presentation.design_system.LabelAndInputFieldContent
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.HIDDEN
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.SHOWN
import com.example.notemark.presentation.design_system.NoteMarkButtonState
import com.example.notemark.presentation.ui.registration.RegistrationAction.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) : ViewModel() {

    var state by mutableStateOf(RegistrationState())
        private set

    fun onAction(action: RegistrationAction) {
        when (action) {
            is UpdateOnScreenUserName -> {
                state = state.copy(
                    userName = LabelAndInputFieldContent(
                        text = action.userName
                    ),
                    buttonState = getButtonState(
                        userName = action.userName
                    )
                )
            }
            is UpdateOnScreenEmail -> {
                state = state.copy(
                    email = LabelAndInputFieldContent(
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
            is UpdateOnScreenRepeatPassword -> {
                state = state.copy(
                    repeatPassword = state.repeatPassword.copy(
                        text = action.repeatPassword
                    ),
                    buttonState = getButtonState(
                        repeatPassword = action.repeatPassword
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
            is UpdateRepeatPasswordVisibility -> {
                state = state.copy(
                    repeatPassword = state.repeatPassword.copy(
                        visibility = if (action.isHidden) HIDDEN else SHOWN
                    )
                )
            }
            GoToLogin -> Unit
        }
    }

    private fun getButtonState(
        userName: String = state.userName.text,
        email: String = state.email.text,
        password: String = state.password.text,
        repeatPassword: String = state.repeatPassword.text,
    ): NoteMarkButtonState {

        val isValidUserName = userName.length in (3..20)
        val isValidEmail = emailValidator.isValidEmail(email)
        val isValidPassword = passwordValidator.isValidPassword(password)
        val isValidRepeatPassword = password == repeatPassword

        return if (isValidUserName && isValidEmail && isValidPassword && isValidRepeatPassword) {
            NoteMarkButtonState.ENABLE
        } else {
            NoteMarkButtonState.DISABLE
        }
    }

}