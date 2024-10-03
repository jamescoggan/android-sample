package com.jamescoggan.sample

import android.app.Application
import com.jamescoggan.sample.di.AppComponent

class SampleApp : Application() {
    private val appComponent by lazy { AppComponent.create(this) }

    fun appComponent() = appComponent
}