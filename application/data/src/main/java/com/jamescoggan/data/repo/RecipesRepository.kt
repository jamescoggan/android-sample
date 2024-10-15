package com.jamescoggan.data.repo

import com.jamescoggan.data.model.Recipe
import com.jamescoggan.data.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface RecipesRepository {
    fun refreshRecommended(): Flow<Resource<Unit>>

    fun recommendedFlow(): Flow<List<Recipe>>
}

class RecipesRepositoryImpl() : RecipesRepository {
    override fun refreshRecommended(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())

        delay(2000L)

        emit(Resource.Success(Unit))
    }.flowOn(Dispatchers.IO)


    override fun recommendedFlow(): Flow<List<Recipe>> {
        return emptyFlow()
    }
}
