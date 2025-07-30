package com.example.notemark.auth.presentation.di

import com.example.notemark.auth.presentation.login.LoginViewModel
import com.example.notemark.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}