package com.example.notemark.auth.domain

class UserDataValidator(
    private val patternValidator: PatternValidator
) {
    fun isValidEmail(email: String): Boolean {
        return patternValidator.matches(email.trim())
    }

    fun isValidPassword(password: String): Boolean {
        val hasMinLength = password.length >= 8
        val hasNumberOrSymbol = password.any { it.isDigit() || !it.isLetterOrDigit() }
        return hasMinLength && hasNumberOrSymbol
    }
}