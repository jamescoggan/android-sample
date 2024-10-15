package com.jamescoggan.data.model

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val category: String,
    val cookingSteps: String
)
