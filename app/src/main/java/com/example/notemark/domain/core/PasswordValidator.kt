package com.example.notemark.domain.core

interface PasswordValidator {

    fun isValidPassword(password: String): Boolean
}