package com.example.notemark.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notemark.auth.domain.AuthRepository
import com.example.notemark.auth.domain.UserDataValidator
import com.example.notemark.core.domain.util.Result
import com.example.notemark.core.presentation.designsystem.LabelAndInputFieldValueVisibility.*
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState.DISABLE
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState.ENABLE
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState.LOADING
import com.example.notemark.auth.presentation.login.LoginAction.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository,
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

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
            OnRegisterClick, ClearMessage -> Unit
            LoginAccount -> {
                loginAccount()
            }
        }
    }

    private fun loginAccount() {
        viewModelScope.launch {
            state = state.copy(buttonState = LOADING)

            val result = repository.login(
                email = state.email.text,
                password = state.password.text,
            )

            when (result) {
                is Result.Success -> {
                    state = state.copy(buttonState = DISABLE)
                    eventChannel.send(LoginEvent.LoginSuccess)
                }
                is Result.Error -> {
                    state = state.copy(buttonState = ENABLE)
                    eventChannel.send(LoginEvent.LoginError(result.error))
                }
            }
        }
    }

    private fun getButtonState(
        email: String = state.email.text,
        password: String = state.password.text,
    ): NoteMarkButtonState {
        if (email.isBlank() || password.isBlank()) return DISABLE

        val isValidEmail = userDataValidator.isValidEmail(email)
        return if (isValidEmail) {
            ENABLE
        } else {
            DISABLE
        }
    }
}