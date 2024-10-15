package com.jamescoggan.data.repo

import com.jamescoggan.data.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun recommendedFlow(): Flow<List<Recipe>>
}

class RecipesRepositoryImpl(): RecipesRepository {
    override fun recommendedFlow(): Flow<List<Recipe>> {
        TODO("Not yet implemented")
    }
}
