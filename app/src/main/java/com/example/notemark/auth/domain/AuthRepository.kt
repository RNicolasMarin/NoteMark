package com.example.notemark.auth.domain

import com.example.notemark.core.domain.util.DataError
import com.example.notemark.core.domain.util.EmptyResult

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.Network>

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): EmptyResult<DataError.Network>
}