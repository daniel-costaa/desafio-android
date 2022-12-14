package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.databaseModule
import com.picpay.desafio.android.di.networkModule
import com.picpay.desafio.android.di.repositoryModule
import com.picpay.desafio.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }
}
