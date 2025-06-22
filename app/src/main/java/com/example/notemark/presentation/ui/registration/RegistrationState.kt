package com.example.notemark.presentation.ui.registration

import com.example.notemark.presentation.design_system.LabelAndInputFieldContent
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.HIDDEN
import com.example.notemark.presentation.design_system.NoteMarkButtonState
import com.example.notemark.presentation.design_system.NoteMarkButtonState.DISABLE
import com.example.notemark.presentation.design_system.PasswordEightPlusCharactersWithNumberOrSymbol
import com.example.notemark.presentation.design_system.UserNameThreeToTwentyCharacters

data class RegistrationState(
    var userName: LabelAndInputFieldContent = defaultUserName,
    var email: LabelAndInputFieldContent = LabelAndInputFieldContent(),
    var password: LabelAndInputFieldContent = defaultPassword,
    var repeatPassword: LabelAndInputFieldContent = defaultRepeatPassword,
    var buttonState: NoteMarkButtonState = DISABLE
)

val defaultUserName = LabelAndInputFieldContent(
    supportingMessage = UserNameThreeToTwentyCharacters
)

val defaultPassword = LabelAndInputFieldContent(
    visibility = HIDDEN,
    supportingMessage = PasswordEightPlusCharactersWithNumberOrSymbol
)

val defaultRepeatPassword = LabelAndInputFieldContent(
    visibility = HIDDEN
)