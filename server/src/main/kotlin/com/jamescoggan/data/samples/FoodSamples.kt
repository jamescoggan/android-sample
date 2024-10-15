package com.jamescoggan.data.samples

import com.jamescoggan.models.FoodItem
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

val carbonaraRecipe = """
    <h2>Spaghetti Carbonara Recipe</h2>
    <p><strong>Ingredients:</strong></p>
    <ul>
        <li>200g Spaghetti</li>
        <li>100g Pancetta or Guanciale</li>
        <li>2 large eggs</li>
        <li>50g Pecorino Romano cheese (grated)</li>
        <li>50g Parmesan cheese (grated)</li>
        <li>Freshly ground black pepper</li>
        <li>Salt</li>
    </ul>

    <p><strong>Instructions:</strong></p>
    <ol>
        <li>Bring a large pot of salted water to a boil, then cook the spaghetti according to package instructions until al dente.</li>
        <li>In a bowl, whisk together the eggs, Pecorino Romano, Parmesan, and black pepper. Set aside.</li>
        <li>In a pan, cook the pancetta or guanciale over medium heat until crispy. Remove from heat.</li>
        <li>Drain the spaghetti, reserving some of the pasta water.</li>
        <li>Add the spaghetti to the pancetta in the pan and toss to coat.</li>
        <li>Remove the pan from heat and quickly stir in the egg and cheese mixture, using a little reserved pasta water to create a creamy sauce.</li>
        <li>Season with more black pepper, and serve immediately with extra Pecorino Romano.</li>
    </ol>
""".trimIndent()

val RecommendedFoods = listOf(
    FoodItem(1, "Spaghetti Carbonara", "https://upload.wikimedia.org/wikipedia/commons/5/57/Spaghetti_alla_Carbonara.jpg", "Italian", carbonaraRecipe.encodeToBase64()),
    FoodItem(2, "Sushi", "https://upload.wikimedia.org/wikipedia/commons/4/4b/Sushi_platter.jpg", "Japanese", carbonaraRecipe.encodeToBase64()),
    FoodItem(3, "Tacos", "https://upload.wikimedia.org/wikipedia/commons/d/db/Tacos_al_Pastor.jpg", "Mexican", carbonaraRecipe.encodeToBase64()),
    FoodItem(4, "Butter Chicken", "https://upload.wikimedia.org/wikipedia/commons/0/05/Butter_chicken.jpg", "Indian", carbonaraRecipe.encodeToBase64()),
)

val FavoriteFoods = listOf(
FoodItem(5, "Falafel", "https://upload.wikimedia.org/wikipedia/commons/8/8a/Falafel_with_tahini.jpg", "Middle Eastern", carbonaraRecipe.encodeToBase64()),
FoodItem(6, "Croissant", "https://upload.wikimedia.org/wikipedia/commons/e/e7/Croissant_%28croissant%29.jpg", "French", carbonaraRecipe.encodeToBase64()),
FoodItem(7, "Chow Mein", "https://upload.wikimedia.org/wikipedia/commons/5/54/Chow_mein.jpg", "Chinese", carbonaraRecipe.encodeToBase64()),
FoodItem(8, "Poutine", "https://upload.wikimedia.org/wikipedia/commons/9/9c/Poutine.jpg", "Canadian", carbonaraRecipe.encodeToBase64()),
FoodItem(9, "Paella", "https://upload.wikimedia.org/wikipedia/commons/8/8b/Paella_valenciana.jpg", "Spanish", carbonaraRecipe.encodeToBase64()),
FoodItem(10, "Goulash", "https://upload.wikimedia.org/wikipedia/commons/8/88/Hungarian_goulash.jpg", "Hungarian", carbonaraRecipe.encodeToBase64())
)

@OptIn(ExperimentalEncodingApi::class)
fun String.encodeToBase64(): String {
    return Base64.encode(this.encodeToByteArray())
}


