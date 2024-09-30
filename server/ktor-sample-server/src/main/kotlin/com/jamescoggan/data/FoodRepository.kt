package com.jamescoggan.data

import com.jamescoggan.data.samples.FavoriteFoods
import com.jamescoggan.data.samples.RecommendedFoods
import com.jamescoggan.models.FoodItem

class FoodRepositoryImpl : FoodRepository {
    override fun getRecommended(): List<FoodItem> = RecommendedFoods

    override fun getFavourite(): List<FoodItem> = FavoriteFoods
}

interface FoodRepository {
    fun getRecommended(): List<FoodItem>

    fun getFavourite(): List<FoodItem>
}