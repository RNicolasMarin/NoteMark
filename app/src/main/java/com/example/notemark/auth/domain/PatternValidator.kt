package com.example.notemark.auth.domain

interface PatternValidator {

    fun matches(value: String): Boolean
}