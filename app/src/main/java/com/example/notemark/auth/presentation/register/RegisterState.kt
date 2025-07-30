package com.example.notemark.auth.presentation.register

import com.example.notemark.core.presentation.designsystem.LabelAndInputFieldContent
import com.example.notemark.core.presentation.designsystem.LabelAndInputFieldValueVisibility.HIDDEN
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState.DISABLE
import com.example.notemark.core.presentation.designsystem.PasswordEightPlusCharactersWithNumberOrSymbol
import com.example.notemark.core.presentation.designsystem.UserNameThreeToTwentyCharacters

data class RegistrationState(
    var userName: LabelAndInputFieldContent = defaultUserName,
    var email: LabelAndInputFieldContent = LabelAndInputFieldContent(),
    var password: LabelAndInputFieldContent = defaultPassword,
    var repeatPassword: LabelAndInputFieldContent = defaultRepeatPassword,
    var buttonState: NoteMarkButtonState = DISABLE
)

val defaultUserName = LabelAndInputFieldContent(
    supportingMessage = UserNameThreeToTwentyCharacters,
)

val defaultPassword = LabelAndInputFieldContent(
    visibility = HIDDEN,
    supportingMessage = PasswordEightPlusCharactersWithNumberOrSymbol,
)

val defaultRepeatPassword = LabelAndInputFieldContent(
    visibility = HIDDEN,
)