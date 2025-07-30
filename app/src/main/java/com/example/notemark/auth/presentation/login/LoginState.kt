package com.example.notemark.auth.presentation.login

import com.example.notemark.core.presentation.designsystem.LabelAndInputFieldContent
import com.example.notemark.core.presentation.designsystem.LabelAndInputFieldValueVisibility.*
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState.DISABLE

data class LoginState(
    var email: LabelAndInputFieldContent = LabelAndInputFieldContent(),
    var password: LabelAndInputFieldContent = LabelAndInputFieldContent().copy(visibility = HIDDEN),
    var buttonState: NoteMarkButtonState = DISABLE
)
