package com.example.notemark.presentation.ui.registration

import com.example.notemark.domain.NoteMarkResult

interface RegistrationEvent {

    data object RegistrationSuccess: RegistrationEvent

    data class RegistrationError(
        val error: NoteMarkResult.Error
    ): RegistrationEvent
}