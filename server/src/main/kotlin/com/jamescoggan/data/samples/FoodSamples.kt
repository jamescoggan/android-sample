package com.jamescoggan.data.samples

import com.jamescoggan.models.FoodItem
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

val RecommendedFoods = listOf(
    FoodItem(
        1,
        "Spaghetti Carbonara",
        "https://upload.wikimedia.org/wikipedia/commons/5/57/Spaghetti_alla_Carbonara.jpg",
        "Italian",
        carbonaraSteps.encodeToBase64()
    ),
    FoodItem(
        2,
        "Sushi",
        "https://upload.wikimedia.org/wikipedia/commons/4/4b/Sushi_platter.jpg",
        "Japanese",
        sushiSteps.encodeToBase64()
    ),
    FoodItem(
        3,
        "Tacos",
        "https://upload.wikimedia.org/wikipedia/commons/d/db/Tacos_al_Pastor.jpg",
        "Mexican",
        tacosSteps.encodeToBase64()
    ),
    FoodItem(
        4,
        "Butter Chicken",
        "https://upload.wikimedia.org/wikipedia/commons/0/05/Butter_chicken.jpg",
        "Indian",
        butterChickenSteps.encodeToBase64()
    ),
)

val FavoriteFoods = listOf(
    FoodItem(
        5,
        "Falafel",
        "https://upload.wikimedia.org/wikipedia/commons/8/8a/Falafel_with_tahini.jpg",
        "Middle Eastern",
        falafelSteps.encodeToBase64()
    ), FoodItem(
        6,
        "Croissant",
        "https://upload.wikimedia.org/wikipedia/commons/e/e7/Croissant_%28croissant%29.jpg",
        "French",
        crossaintSteps.encodeToBase64()
    ), FoodItem(
        7,
        "Chow Mein",
        "https://upload.wikimedia.org/wikipedia/commons/5/54/Chow_mein.jpg",
        "Chinese",
        chowMeinSteps.encodeToBase64()
    ), FoodItem(
        8,
        "Poutine",
        "https://upload.wikimedia.org/wikipedia/commons/9/9c/Poutine.jpg",
        "Canadian",
        poutineSteps.encodeToBase64()
    ), FoodItem(
        9,
        "Paella",
        "https://upload.wikimedia.org/wikipedia/commons/8/8b/Paella_valenciana.jpg",
        "Spanish",
        paellaSteps.encodeToBase64()
    ), FoodItem(
        10,
        "Goulash",
        "https://upload.wikimedia.org/wikipedia/commons/8/88/Hungarian_goulash.jpg",
        "Hungarian",
        goulashSteps.encodeToBase64()
    )
)

@OptIn(ExperimentalEncodingApi::class)
fun String.encodeToBase64(): String {
    return Base64.encode(this.encodeToByteArray())
}


