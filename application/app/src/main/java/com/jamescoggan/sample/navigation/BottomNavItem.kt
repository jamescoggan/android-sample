package com.jamescoggan.sample.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.jamescoggan.sample.favorites.FavoritesScreen
import com.jamescoggan.sample.recipes.RecipesScreen
import com.slack.circuit.runtime.screen.Screen

private const val RECIPES_SCREEN_NAME = "Recipes"
private const val FAVORITES_SCREEN_NAME = "Favorites"

sealed class BottomNavItem(val title: String, val icon: ImageVector) {
    abstract val screen: Screen

    data object Recipes : BottomNavItem(RECIPES_SCREEN_NAME, Icons.Filled.Home) {
        override val screen: Screen = RecipesScreen()
    }

    data object Favorites : BottomNavItem(FAVORITES_SCREEN_NAME, Icons.Filled.Info) {
        override val screen: Screen = FavoritesScreen()
    }
}
