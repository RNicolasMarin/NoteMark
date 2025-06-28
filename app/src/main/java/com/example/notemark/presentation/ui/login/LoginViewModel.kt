package com.example.notemark.presentation.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notemark.domain.NoteMarkResult
import com.example.notemark.domain.core.EmailValidator
import com.example.notemark.domain.repositories.SessionRepository
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.*
import com.example.notemark.presentation.design_system.NoteMarkButtonState
import com.example.notemark.presentation.design_system.NoteMarkButtonState.DISABLE
import com.example.notemark.presentation.design_system.NoteMarkButtonState.ENABLE
import com.example.notemark.presentation.design_system.NoteMarkButtonState.LOADING
import com.example.notemark.presentation.ui.login.LoginAction.*
import com.example.notemark.presentation.ui.login.LoginEvent.LoginError
import com.example.notemark.presentation.ui.login.LoginEvent.LoginSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailValidator: EmailValidator,
    private val sessionRepository: SessionRepository
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
            GoToRegister, ClearMessage -> Unit
            LoginAccount -> {
                loginAccount()
            }
        }
    }

    private fun loginAccount() {
        state = state.copy(
            buttonState = LOADING
        )

        viewModelScope.launch(Dispatchers.IO) {
            val result = sessionRepository.login(
                email = state.email.text,
                password = state.password.text,
            )

            withContext(Dispatchers.Main) {
                when (result) {
                    is NoteMarkResult.Success<*> -> {
                        state = state.copy(
                            buttonState = DISABLE
                        )
                        eventChannel.send(LoginSuccess)
                    }
                    is NoteMarkResult.Error -> {
                        state = state.copy(
                            buttonState = ENABLE
                        )
                        eventChannel.send(LoginError(result))
                    }
                }
            }
        }
    }

    private fun getButtonState(
        email: String = state.email.text,
        password: String = state.password.text,
    ): NoteMarkButtonState {
        if (email.isBlank() || password.isBlank()) return DISABLE

        val isValidEmail = emailValidator.isValidEmail(email)
        return if (isValidEmail) {
            ENABLE
        } else {
            DISABLE
        }
    }
}