package com.example.notemark.presentation.design_system

import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.*

data class LabelAndInputFieldContent(
    val text: String = "",
    val visibility: LabelAndInputFieldValueVisibility = NONE,
    val message: LabelAndInputFieldContentMessage? = null
)

enum class LabelAndInputFieldValueVisibility {
    HIDDEN,
    SHOWN,
    NONE
}

abstract class LabelAndInputFieldContentMessage

abstract class LabelAndInputFieldContentSupportingMessage: LabelAndInputFieldContentMessage()
abstract class LabelAndInputFieldContentErrorMessage: LabelAndInputFieldContentMessage()
