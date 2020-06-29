package com.haurbano.mymercadolibre

import android.app.Application
import com.haurbano.data.di.repositoriesModule
import com.haurbano.domain.di.useCasesModule
import com.haurbano.presentation.di.presentationUtilsModule
import com.haurbano.presentation.di.viewModelsModule
import com.haurbano.remotedatasource.di.apisModule
import com.haurbano.remotedatasource.di.mappersModule
import com.haurbano.remotedatasource.di.remoteDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyMercadolibreApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyMercadolibreApp)
            modules(
                useCasesModule,
                repositoriesModule,
                remoteDataSourceModule,
                mappersModule,
                apisModule,
                viewModelsModule,
                presentationUtilsModule
            )
        }
    }
}