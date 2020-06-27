package com.haurbano.mymercadolibre

import android.app.Application
import com.haurbano.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyMercadolibreApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(useCasesModule)
            androidContext(this@MyMercadolibreApp)
        }
    }
}