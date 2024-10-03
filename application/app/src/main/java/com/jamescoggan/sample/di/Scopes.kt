package com.jamescoggan.sample.di

import javax.inject.Scope
import kotlin.reflect.KClass

@Suppress("unused")
@Scope
annotation class SingleIn(val scope: KClass<*>)

class AppScope private constructor()
