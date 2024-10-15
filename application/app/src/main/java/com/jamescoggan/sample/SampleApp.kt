package com.jamescoggan.sample

import android.app.Application
import com.jamescoggan.sample.di.AppComponent
import timber.log.Timber

class SampleApp : Application() {
    private val appComponent by lazy { AppComponent.create(this) }

    fun appComponent() = appComponent

    override fun onCreate() {
        super.onCreate()

        // Todo: only load up in debug mode
        Timber.plant(Timber.DebugTree())
    }
}