package com.example.notemark.app

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

    @Serializable
    data object Notes: Screen()
}