package com.example.notemark.domain.core

import android.util.Patterns.EMAIL_ADDRESS

class EmailValidatorImpl: EmailValidator {

    override fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }
}