package com.example.notemark.presentation.ui.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notemark.domain.core.EmailValidator
import com.example.notemark.domain.core.PasswordValidator
import com.example.notemark.presentation.design_system.EmailInvalid
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.HIDDEN
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.SHOWN
import com.example.notemark.presentation.design_system.NoteMarkButtonState
import com.example.notemark.presentation.design_system.PasswordAtLeastEightCharactersWithNumberOrSymbol
import com.example.notemark.presentation.design_system.RepeatPasswordDoNotMatch
import com.example.notemark.presentation.design_system.UserNameAtLeastThreeCharacters
import com.example.notemark.presentation.design_system.UserNameAtMostTwentyCharacters
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
                    userName = state.userName.copy(
                        text = action.userName
                    )
                )
                updateButtonStateAndErrors(userName = action.userName)
            }
            is UpdateOnScreenEmail -> {
                state = state.copy(
                    email = state.email.copy(
                        text = action.email
                    )
                )
                updateButtonStateAndErrors(email = action.email)
            }
            is UpdateOnScreenPassword -> {
                state = state.copy(
                    password = state.password.copy(
                        text = action.password
                    )
                )
                updateButtonStateAndErrors(password = action.password)
            }
            is UpdateOnScreenRepeatPassword -> {
                state = state.copy(
                    repeatPassword = state.repeatPassword.copy(
                        text = action.repeatPassword
                    )
                )
                updateButtonStateAndErrors(repeatPassword = action.repeatPassword)
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

    private fun updateButtonStateAndErrors(
        userName: String = state.userName.text,
        email: String = state.email.text,
        password: String = state.password.text,
        repeatPassword: String = state.repeatPassword.text,
    ) {
        val userNameError = when {
            userName.length < 3 -> UserNameAtLeastThreeCharacters
            userName.length > 20 -> UserNameAtMostTwentyCharacters
            else -> null
        }

        val emailError = when {
            !emailValidator.isValidEmail(email) -> EmailInvalid
            else -> null
        }

        val passwordError = when {
            !passwordValidator.isValidPassword(password) -> PasswordAtLeastEightCharactersWithNumberOrSymbol
            else -> null
        }

        val repeatPasswordError = when {
            password != repeatPassword -> RepeatPasswordDoNotMatch
            else -> null
        }

        val buttonState = if (userNameError == null && emailError == null && passwordError == null && repeatPasswordError == null) {
            NoteMarkButtonState.ENABLE
        } else {
            NoteMarkButtonState.DISABLE
        }

        state = state.copy(
            userName = state.userName.copy(
                errorMessage = userNameError
            ),
            email = state.email.copy(
                errorMessage = emailError
            ),
            password = state.password.copy(
                errorMessage = passwordError
            ),
            repeatPassword = state.repeatPassword.copy(
                errorMessage = repeatPasswordError
            ),
            buttonState = buttonState
        )
    }

}