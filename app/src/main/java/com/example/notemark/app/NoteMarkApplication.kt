package com.example.notemark.app

import android.app.Application
import com.example.notemark.app.di.appModule
import com.example.notemark.auth.data.di.authDataModule
import com.example.notemark.auth.presentation.di.authViewModelModule
import com.example.notemark.core.data.di.coreDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteMarkApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NoteMarkApplication)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
            )
        }
    }
}