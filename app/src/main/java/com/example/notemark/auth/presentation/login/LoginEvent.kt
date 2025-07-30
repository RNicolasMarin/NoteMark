package com.example.notemark.auth.presentation.login

import com.example.notemark.core.domain.util.DataError

interface LoginEvent {

    data object LoginSuccess: LoginEvent

    data class LoginError(
        val error: DataError.Network
    ): LoginEvent
}