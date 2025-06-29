package com.example.notemark.data.repositories

import android.content.SharedPreferences
import com.example.notemark.data.makeRequest
import com.example.notemark.data.remote.dto.LoginRequest
import com.example.notemark.data.remote.dto.RegisterRequest
import com.example.notemark.data.remote.services.SessionService
import com.example.notemark.domain.NoteMarkResult
import com.example.notemark.domain.NoteMarkResult.*
import com.example.notemark.domain.NoteMarkResult.Error.UnexpectedResponseError
import com.example.notemark.domain.model.Tokens
import com.example.notemark.domain.repositories.SessionRepository
import com.google.gson.Gson
import javax.inject.Inject
import kotlin.String
import androidx.core.content.edit

class SessionRepositoryImpl @Inject constructor(
    private val service: SessionService,
    private val preferences: SharedPreferences
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
                    val tokens = Tokens(
                        accessToken = accessToken,
                        refreshToken = refreshToken
                    )
                    saveTokens(tokens)
                    Success(
                        tokens
                    )
                }
            }
            is Error -> {
                response
            }
        }
    }

    override suspend fun saveTokens(tokens: Tokens) {
        val gson = Gson()
        val json = gson.toJson(tokens)
        preferences.edit { putString("tokens", json) }
    }

}