package com.example.notemark.core.presentation.designsystem

import com.example.notemark.R

data class LabelAndInputFieldContent(
    val text: String = "",
    val visibility: LabelAndInputFieldValueVisibility = LabelAndInputFieldValueVisibility.NONE,
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
        UserNameThreeToTwentyCharacters -> R.string.register_screen_username_support
        PasswordEightPlusCharactersWithNumberOrSymbol -> R.string.register_screen_password_support

        UserNameAtLeastThreeCharacters -> R.string.register_screen_username_error_at_least_3_characters
        UserNameAtMostTwentyCharacters -> R.string.register_screen_username_error_at_most_20_characters
        EmailInvalid -> R.string.register_screen_email_error_invalid
        PasswordAtLeastEightCharactersWithNumberOrSymbol -> R.string.register_screen_password_error
        RepeatPasswordDoNotMatch -> R.string.register_screen_repeat_password_error

        else -> R.string.register_screen_password_support
    }
}