package com.example.notemark.data.remote.services

import com.example.notemark.data.remote.dto.LoginRequest
import com.example.notemark.data.remote.dto.LoginResponse
import com.example.notemark.data.remote.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SessionService {

    @POST("/api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<Unit>

    @POST("/api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}