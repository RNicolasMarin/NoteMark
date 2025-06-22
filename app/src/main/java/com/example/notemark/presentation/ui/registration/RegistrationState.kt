package com.example.notemark.presentation.ui.registration

import com.example.notemark.presentation.design_system.LabelAndInputFieldContent
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.HIDDEN
import com.example.notemark.presentation.design_system.NoteMarkButtonState
import com.example.notemark.presentation.design_system.NoteMarkButtonState.DISABLE

data class RegistrationState(
    var userName: LabelAndInputFieldContent = LabelAndInputFieldContent(),
    var email: LabelAndInputFieldContent = LabelAndInputFieldContent(),
    var password: LabelAndInputFieldContent = LabelAndInputFieldContent().copy(visibility = HIDDEN),
    var repeatPassword: LabelAndInputFieldContent = LabelAndInputFieldContent().copy(visibility = HIDDEN),
    var buttonState: NoteMarkButtonState = DISABLE
)