package com.example.notemark.presentation.ui.login

import com.example.notemark.domain.NoteMarkResult

interface LoginEvent {

    data object LoginSuccess: LoginEvent

    data class LoginError(
        val error: NoteMarkResult.Error
    ): LoginEvent
}