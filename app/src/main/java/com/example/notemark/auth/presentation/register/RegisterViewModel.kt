package com.example.notemark.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notemark.auth.domain.AuthRepository
import com.example.notemark.auth.domain.UserDataValidator
import com.example.notemark.auth.presentation.register.RegisterAction.*
import com.example.notemark.auth.presentation.register.RegisterEvent.*
import com.example.notemark.core.domain.util.Result
import com.example.notemark.core.presentation.designsystem.EmailInvalid
import com.example.notemark.core.presentation.designsystem.LabelAndInputFieldValueVisibility
import com.example.notemark.core.presentation.designsystem.LabelAndInputFieldValueVisibility.*
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState.*
import com.example.notemark.core.presentation.designsystem.PasswordAtLeastEightCharactersWithNumberOrSymbol
import com.example.notemark.core.presentation.designsystem.RepeatPasswordDoNotMatch
import com.example.notemark.core.presentation.designsystem.UserNameAtLeastThreeCharacters
import com.example.notemark.core.presentation.designsystem.UserNameAtMostTwentyCharacters
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(RegistrationState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RegisterAction) {
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
            GoToLogin, ClearMessage -> Unit
            CreateAccount -> {
                createAccount()
            }
        }
    }

    private fun createAccount() {
        viewModelScope.launch {
            state = state.copy(buttonState = LOADING)

            val result = repository.register(
                username = state.userName.text,
                email = state.email.text,
                password = state.password.text,
            )

            when (result) {
                is Result.Success -> {
                    state = state.copy(buttonState = DISABLE)
                    eventChannel.send(RegistrationSuccess)
                }
                is Result.Error -> {
                    state = state.copy(buttonState = ENABLE)
                    eventChannel.send(RegistrationError(result.error))
                }
            }
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
            !userDataValidator.isValidEmail(email) -> EmailInvalid
            else -> null
        }

        val passwordError = when {
            !userDataValidator.isValidPassword(password) -> PasswordAtLeastEightCharactersWithNumberOrSymbol
            else -> null
        }

        val repeatPasswordError = when {
            password != repeatPassword -> RepeatPasswordDoNotMatch
            else -> null
        }

        val buttonState = if (userNameError == null && emailError == null && passwordError == null && repeatPasswordError == null) {
            ENABLE
        } else {
            DISABLE
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