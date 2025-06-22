package com.example.notemark.domain.core

class PasswordValidatorImpl: PasswordValidator {

    override fun isValidPassword(password: String): Boolean {
        val hasMinLength = password.length >= 8
        val hasNumberOrSymbol = password.any { it.isDigit() || !it.isLetterOrDigit() }
        return hasMinLength && hasNumberOrSymbol
    }
}