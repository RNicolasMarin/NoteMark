package com.example.notemark.domain.repositories

import com.example.notemark.domain.NoteMarkResult

interface SessionRepository {

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): NoteMarkResult<Unit>

}