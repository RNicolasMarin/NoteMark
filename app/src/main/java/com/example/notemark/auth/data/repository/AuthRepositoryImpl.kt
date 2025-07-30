package com.example.notemark.auth.data.repository

import com.example.notemark.auth.data.dto.LoginRequest
import com.example.notemark.auth.data.dto.LoginResponse
import com.example.notemark.auth.data.dto.RegisterRequest
import com.example.notemark.auth.domain.AuthRepository
import com.example.notemark.core.data.networking.post
import com.example.notemark.core.domain.AuthInfo
import com.example.notemark.core.domain.SessionStorage
import com.example.notemark.core.domain.util.DataError
import com.example.notemark.core.domain.util.EmptyResult
import com.example.notemark.core.domain.util.Result
import com.example.notemark.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/api/auth/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        )

        if(result is Result.Success) {
            val accessToken = result.data.accessToken
            val refreshToken = result.data.refreshToken
            if (accessToken != null && refreshToken != null) {
                sessionStorage.set(
                    AuthInfo(
                        accessToken = accessToken,
                        refreshToken = refreshToken,
                    )
                )
            }
        }
        return result.asEmptyDataResult()
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/api/auth/register",
            body = RegisterRequest(
                username = username,
                email = email,
                password = password
            )
        )
    }

}