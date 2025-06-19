package com.example.notemark.presentation.ui.login

import com.example.notemark.presentation.design_system.NoteMarkButtonState
import com.example.notemark.presentation.design_system.NoteMarkButtonState.DISABLE

data class LoginState(
    var email: String = "",
    var password: String = "",
    var isPasswordHidden: Boolean = true,
    var buttonState: NoteMarkButtonState = DISABLE
)
