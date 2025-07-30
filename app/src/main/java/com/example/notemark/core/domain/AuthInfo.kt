package com.example.notemark.core.domain

data class AuthInfo(
    val accessToken: String,//short live
    val refreshToken: String,//long live
)