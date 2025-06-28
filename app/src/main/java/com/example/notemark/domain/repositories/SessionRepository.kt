package com.example.notemark.domain.repositories

import com.example.notemark.domain.NoteMarkResult
import com.example.notemark.domain.model.Tokens

interface SessionRepository {

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): NoteMarkResult<Unit>

    suspend fun login(
        email: String,
        password: String
    ): NoteMarkResult<Tokens>

}