package com.example.notemark.auth.presentation.register

import com.example.notemark.core.domain.util.DataError

interface RegisterEvent {

    data object RegistrationSuccess: RegisterEvent

    data class RegistrationError(
        val error: DataError.Network
    ): RegisterEvent
}