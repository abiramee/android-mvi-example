package com.abiramee.meetmax

import android.app.Application
import com.abiramee.meetmax.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MeetMaxApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MeetMaxApplication)
            androidLogger()

            modules(appModule)
        }
    }
}