package com.example.notemark.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val accessToken: String? = null,
    val refreshToken: String? = null,
): Parcelable
