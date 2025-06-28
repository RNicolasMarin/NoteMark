package com.example.notemark.data.repositories

import com.example.notemark.data.makeRequest
import com.example.notemark.data.remote.dto.LoginRequest
import com.example.notemark.data.remote.dto.RegisterRequest
import com.example.notemark.data.remote.services.SessionService
import com.example.notemark.domain.NoteMarkResult
import com.example.notemark.domain.NoteMarkResult.*
import com.example.notemark.domain.NoteMarkResult.Error.UnexpectedResponseError
import com.example.notemark.domain.model.Tokens
import com.example.notemark.domain.repositories.SessionRepository
import javax.inject.Inject
import kotlin.String

class SessionRepositoryImpl @Inject constructor(
    private val service: SessionService
): SessionRepository {

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): NoteMarkResult<Unit> {
        return makeRequest {
            service.register(
                RegisterRequest(
                    username = username,
                    email = email,
                    password = password
                )
            )
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): NoteMarkResult<Tokens> {
        val response = makeRequest {
            service.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )
        }
        return when (response) {
            is Success -> {
                val accessToken = response.data.accessToken
                val refreshToken = response.data.refreshToken

                if (accessToken == null || refreshToken == null) {
                    UnexpectedResponseError
                } else {
                    Success(
                        Tokens(
                            accessToken = accessToken,
                            refreshToken = refreshToken
                        )
                    )
                }
            }
            is Error -> {
                response
            }
        }
    }

}