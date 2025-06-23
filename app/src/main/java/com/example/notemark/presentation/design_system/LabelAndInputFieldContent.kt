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

data object UserNameAtLeastThreeCharacters: LabelAndInputFieldContentErrorMessage()
data object UserNameAtMostTwentyCharacters: LabelAndInputFieldContentErrorMessage()
data object EmailInvalid: LabelAndInputFieldContentErrorMessage()
data object PasswordAtLeastEightCharactersWithNumberOrSymbol: LabelAndInputFieldContentErrorMessage()
data object RepeatPasswordDoNotMatch: LabelAndInputFieldContentErrorMessage()

fun LabelAndInputFieldContentMessage.getMessageResource(): Int {
    return when (this) {
        UserNameThreeToTwentyCharacters -> R.string.registration_screen_username_support
        PasswordEightPlusCharactersWithNumberOrSymbol -> R.string.registration_screen_password_support

        UserNameAtLeastThreeCharacters -> R.string.registration_screen_username_error_at_least_3_characters
        UserNameAtMostTwentyCharacters -> R.string.registration_screen_username_error_at_most_20_characters
        EmailInvalid -> R.string.registration_screen_email_error_invalid
        PasswordAtLeastEightCharactersWithNumberOrSymbol -> R.string.registration_screen_password_error
        RepeatPasswordDoNotMatch -> R.string.registration_screen_repeat_password_error

        else -> R.string.registration_screen_password_support
    }
}