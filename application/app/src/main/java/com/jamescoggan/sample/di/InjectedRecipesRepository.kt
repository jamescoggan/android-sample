package com.jamescoggan.sample.di

import com.jamescoggan.data.repo.RecipesRepository
import com.jamescoggan.data.repo.RecipesRepositoryImpl
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class InjectedRecipesRepository
@Inject
constructor() : RecipesRepository by RecipesRepositoryImpl()
