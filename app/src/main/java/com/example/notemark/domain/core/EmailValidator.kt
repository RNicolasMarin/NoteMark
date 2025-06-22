package com.example.notemark.domain.core

interface EmailValidator {

    fun isValidEmail(email: String): Boolean

}