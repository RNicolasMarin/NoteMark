package com.example.notemark.core.domain.util

sealed interface DataError: Error {
    enum class Network: DataError {
        FORMAT_ERROR,
        UNAUTHORIZED,//invalid token or password
        METHOD_ERROR,
        CONFLICT,//email already exists on backend
        TOO_MANY_REQUESTS,//api key request limit surpassed
        NO_INTERNET,
        SERVER_ERROR,//we make a request that makes the server crash, it's not the app's fault but we need to show it
        SERIALIZATION,//format when parsing response
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL //not enough space or not access to a file
    }
}