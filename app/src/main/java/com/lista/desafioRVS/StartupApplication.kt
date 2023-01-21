package com.lista.desafioRVS

import android.app.Application
import com.lista.desafioRVS.features.main.di.bookModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StartupApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val koin = KoinApplication.init()
            .printLogger(Level.ERROR)
            .modules(modules())
            .androidContext(this)
        startKoin(koin)
    }

    private fun modules() = arrayListOf(bookModule)
}