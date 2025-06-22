package com.example.notemark.domain.di

import com.example.notemark.domain.core.EmailValidator
import com.example.notemark.domain.core.EmailValidatorImpl
import com.example.notemark.domain.core.PasswordValidator
import com.example.notemark.domain.core.PasswordValidatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ValidatorModule {

    @Provides
    fun provideEmailValidator(): EmailValidator {
        return EmailValidatorImpl()
    }

    @Provides
    fun providePasswordValidator(): PasswordValidator {
        return PasswordValidatorImpl()
    }
}