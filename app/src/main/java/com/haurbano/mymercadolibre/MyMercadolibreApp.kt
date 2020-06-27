package com.haurbano.mymercadolibre

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyMercadolibreApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyMercadolibreApp)
        }
    }
}