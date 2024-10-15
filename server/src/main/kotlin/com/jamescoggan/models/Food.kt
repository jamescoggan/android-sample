package com.jamescoggan.models

import kotlinx.serialization.Serializable

@Serializable
data class FoodItem(
    val id: Int,
    val name: String,
    val image: String,
    val cuisine: String,
    val cookingSteps: String,
)
