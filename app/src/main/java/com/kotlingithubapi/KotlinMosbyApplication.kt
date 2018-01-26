package com.kotlingithubapi

import android.app.Application
import com.kotlingithubapi.di.AppComponent
import com.kotlingithubapi.di.AppModule
import com.kotlingithubapi.di.DaggerAppComponent
import timber.log.Timber

/**
 * Created by Valentyn on 16.07.2017.
 */
class KotlinMosbyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent = buildComponent()
        appComponent.inject(this)

    }

    private fun buildComponent(): AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

}