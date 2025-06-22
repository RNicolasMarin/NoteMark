package com.example.notemark.domain.core

class EmailValidatorImpl: EmailValidator {

    override fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"))
    }
}