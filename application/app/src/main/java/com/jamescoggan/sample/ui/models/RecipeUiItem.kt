package com.jamescoggan.sample.ui.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jamescoggan.data.model.Recipe
import com.jamescoggan.sample.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

enum class RecipeCategory(@Suppress("unused") val categoryName: String) {
    CHINESE("chinese"), SPANISH("spanish"), INDIAN("indian");
}


data class RecipeUiItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val category: RecipeCategory,
    val cookingSteps: String
)

@Composable
fun RecipeCategory.label(): String = when (this) {
    RecipeCategory.CHINESE -> stringResource(R.string.category_title_chinese)
    RecipeCategory.SPANISH -> stringResource(R.string.category_title_spanish)
    RecipeCategory.INDIAN -> stringResource(R.string.category_title_indian)
}

fun List<Recipe>.toUiItems(): ImmutableList<RecipeUiItem> =
    this.map { it.toRecipeUiItem() }.toImmutableList()

fun Recipe.toRecipeUiItem(): RecipeUiItem = RecipeUiItem(
    this.id,
    this.title,
    this.imageUrl,
    RecipeCategory.valueOf(this.category.uppercase()),
    this.cookingSteps
)
