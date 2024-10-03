package com.jamescoggan.sample.di

import android.app.Activity
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.multibindings.Multibinds

@ContributesTo(AppScope::class)
@Module
interface BaseUiModule {
    @Multibinds
    fun provideActivityProviders(): Map<Class<out Activity>, Activity>
}
