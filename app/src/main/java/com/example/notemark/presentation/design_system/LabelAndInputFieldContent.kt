package com.example.notemark.presentation.design_system

import com.example.notemark.R
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.*

data class LabelAndInputFieldContent(
    val text: String = "",
    val visibility: LabelAndInputFieldValueVisibility = NONE,
    val supportingMessage: LabelAndInputFieldContentSupportingMessage? = null,
    val errorMessage: LabelAndInputFieldContentErrorMessage? = null,
)

enum class LabelAndInputFieldValueVisibility {
    HIDDEN,
    SHOWN,
    NONE
}

abstract class LabelAndInputFieldContentMessage

abstract class LabelAndInputFieldContentSupportingMessage: LabelAndInputFieldContentMessage()
abstract class LabelAndInputFieldContentErrorMessage: LabelAndInputFieldContentMessage()

data object UserNameThreeToTwentyCharacters: LabelAndInputFieldContentSupportingMessage()
data object PasswordEightPlusCharactersWithNumberOrSymbol: LabelAndInputFieldContentSupportingMessage()

fun LabelAndInputFieldContentMessage.getMessageResource(): Int {
    return when (this) {
        UserNameThreeToTwentyCharacters -> R.string.registration_screen_username_support
        PasswordEightPlusCharactersWithNumberOrSymbol -> R.string.registration_screen_password_support
        else -> R.string.registration_screen_password_support
    }
}