package com.jamescoggan.sample.ui.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jamescoggan.sample.R

enum class RecipeCategory {
    CHINESE, SPANISH
}


data class RecipeUiItem(
    val id: Int, val title: String, val imageUrl: String, val category: RecipeCategory
)

@Composable
fun RecipeCategory.label(): String = when (this) {
    RecipeCategory.CHINESE -> stringResource(R.string.category_title_chinese)
    RecipeCategory.SPANISH -> stringResource(R.string.category_title_spanish)
}

