package com.example.notemark.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterRequest(
    @SerializedName("username")
    val username: String,
    val email: String,
    val password: String,
): Parcelable