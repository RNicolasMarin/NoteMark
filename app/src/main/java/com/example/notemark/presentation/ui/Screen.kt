package com.example.notemark.presentation.ui

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object Landing: Screen()

    @Serializable
    data class Login(
        val messageRes: Int
    ): Screen()

    @Serializable
    data object Registration: Screen()
}