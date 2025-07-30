package com.example.notemark.auth.data.di

import com.example.notemark.auth.data.EmailPatternValidator
import com.example.notemark.auth.data.repository.AuthRepositoryImpl
import com.example.notemark.auth.domain.AuthRepository
import com.example.notemark.auth.domain.PatternValidator
import com.example.notemark.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}